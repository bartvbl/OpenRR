package openrr.world.buildings.placers;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.Orientation;
import openrr.map.soil.SoilType;
import openrr.map.world.MapTileReader;
import openrr.map.world.MapWorldUtils;
import openrr.map.world.events.MapSoilUpdate;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRMessageType;
import openrr.world.core.ORRPropertyDataType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.Property;
import orre.geom.Point2D;
import orre.input.InputEvent;
import orre.sceneGraph.SceneNode;


public abstract class BuildingPlacer extends Property {

	private PlaceVisualiser placerAppearance;
	private final ORRGameObjectType buildingType;
	private MapTileReader reader;
	private final TileContents[][] buildingMap;
	private int mouseProbeID;
	private int mapID;
	private boolean wasClicked = false;
	private int buildingX;
	private int buildingY;
	private Orientation orientation;
	
	public BuildingPlacer(Enum<?> type, GameObject gameObject, TileContents[][] buildingMap, ORRGameObjectType building) {
		super(type, gameObject);
		this.buildingType = building;
		this.buildingMap = buildingMap;
	}
	
	@Override
	public void handleMessage(Message<?> message) {
		InputEvent event = (InputEvent) message.getPayload();
		if(!wasClicked) {
			this.placeBuilding();
			this.gameObject.world.despawnObject(this.gameObject.id);
		}
		wasClicked = event.value == 1.0;
	}

	private void placeBuilding() {
		//int gameObjectID = gameObject.world.spawnGameObject(buildingType);
		//start teleport animation
		//update map
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if((buildingMap[i + 1][j + 1] == TileContents.BUILDING) || (buildingMap[i + 1][j + 1] == TileContents.POWER_PATH)) {
					MapSoilUpdate update = new MapSoilUpdate(new Point2D(buildingX + i, buildingY + j), SoilType.POWER_PATH_SQUARE_UNPOWERED);
					gameObject.world.dispatchMessage(new Message<MapSoilUpdate>(ORRMessageType.TILE_UPDATE, update), mapID);
				}
			}
		}
		gameObject.world.spawnGameObject(buildingType);
	}

	@Override
	public void tick() {
		//1. get mouse map coordinates
		Vector3f mouseLocation = (Vector3f) gameObject.world.requestPropertyData(mouseProbeID, ORRPropertyDataType.MOUSE_LOCATION, null, Vector3f.class);
		//2. update orientation and location
		this.buildingX = (int) (mouseLocation.x + 0.5);
		this.buildingY = (int) (mouseLocation.y + 0.5);
		this.orientation = getOrientation(mouseLocation);
		placerAppearance.updatePosition(buildingX, buildingY, orientation);
		//3. verify possibility to place building here
	}

	private Orientation getOrientation(Vector3f mouseLocation) {
		double tileX = mouseLocation.x + 0.5 - Math.floor(mouseLocation.x + 0.5);
		double tileY = mouseLocation.y + 0.5 - Math.floor(mouseLocation.y + 0.5);
		if(tileX > tileY) {
			if(tileX > 1d-tileY) {
				return Orientation.north;
			} else {
				return Orientation.west;
			}
		} else {
			if(tileX > 1d-tileY) {
				return Orientation.east;
			} else {
				return Orientation.south;
			}
		}
	}

	@Override
	public void destroy() {
		gameObject.world.services.inputService.removeCommandListener(this.gameObject.id, "select");
		SceneNode mapNode = MapWorldUtils.getMapRoot(gameObject.world);
		mapNode.removeChild(placerAppearance);
	}

	@Override
	public void init() {
		SceneNode mapNode = MapWorldUtils.getMapRoot(gameObject.world);
		this.mouseProbeID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MOUSE_TRACKER);
		this.mapID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		this.placerAppearance = new PlaceVisualiser(buildingMap, reader);
		mapNode.addChild(placerAppearance);
		gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "select");
	}

}
