package openrr.world.buildings.placers;

import java.util.Arrays;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.Orientation;
import openrr.map.soil.SoilType;
import openrr.map.world.MapTileReader;
import openrr.map.world.MapWorldUtils;
import openrr.map.world.events.MapSoilUpdate;
import openrr.world.buildings.animations.BuildingAnimationGenerator;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRMessageType;
import openrr.world.core.ORRPropertyDataType;
import orre.animation.Animation;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point2D;
import orre.geom.mesh.Model;
import orre.input.InputEvent;
import orre.sceneGraph.SceneNode;


public abstract class BuildingPlacer extends Property {

	private PlaceVisualiser placerAppearance;
	private final ORRGameObjectType buildingType;
	private MapTileReader reader;
	private final TileContents[][] buildingMap;
	private final TileContents[][] rotatedBuildingMap = new TileContents[3][3];
	private int mouseProbeID;
	private int mapID;
	private int buildingX;
	private int buildingY;
	private Orientation orientation;
	
	public BuildingPlacer(Enum<?> type, GameObject gameObject, TileContents[][] buildingMap, ORRGameObjectType building) {
		super(type, gameObject, true);
		this.buildingType = building;
		this.buildingMap = buildingMap;
	}
	
	@Override
	public void handleMessage(Message<?> message) {
		InputEvent event = (InputEvent) message.getPayload();
		if(event.command.equals("select")) {
			this.placeBuilding();
		}// else: command = "back" -> despawn placer
		
		//already de-register here.
		gameObject.world.services.inputService.removeCommandListener(this.gameObject.id, "select");
		gameObject.world.services.inputService.removeCommandListener(this.gameObject.id, "back");
		
		this.gameObject.world.despawnObject(this.gameObject.id);
	}

	private void placeBuilding() {
		//int gameObjectID = gameObject.world.spawnGameObject(buildingType);
		//start teleport animation
		//update map
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if((rotatedBuildingMap[i + 1][j + 1] == TileContents.BUILDING) || (rotatedBuildingMap[i + 1][j + 1] == TileContents.POWER_PATH)) {
					MapSoilUpdate update = new MapSoilUpdate(new Point2D(buildingX + i, buildingY + j), SoilType.POWER_PATH_SQUARE_UNPOWERED);
					gameObject.world.dispatchMessage(new Message<MapSoilUpdate>(ORRMessageType.TILE_UPDATE, update), mapID);
				}
			}
		}
		int buildingID = gameObject.world.spawnGameObject(buildingType);
		Model model = (Model) gameObject.world.requestPropertyData(buildingID, PropertyDataType.APPEARANCE, null, Model.class);
		model.getRootNode().setLocation(buildingX, buildingY, 0);
		model.getRootNode().setRotation(0, 0, getRotationAngle());
		Animation teleportAnimation = BuildingAnimationGenerator.generateAnimationFor(model);
		this.gameObject.world.services.animationService.applyAnimation(teleportAnimation, model);
		
		
	}

	@Override
	public void tick() {
		//1. get mouse map coordinates
		Vector3f mouseLocation = (Vector3f) gameObject.world.requestPropertyData(mouseProbeID, ORRPropertyDataType.MOUSE_LOCATION, null, Vector3f.class);
		//2. update orientation and location
		this.buildingX = (int) (mouseLocation.x + 0.5);
		this.buildingY = (int) (mouseLocation.y + 0.5);
		this.orientation = getOrientation(mouseLocation);
		updateRotatedBuildingMap();
		this.placerAppearance.updatePosition(buildingX, buildingY, rotatedBuildingMap);
		//3. verify possibility to place building here
	}

	private void updateRotatedBuildingMap() {
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				double rotationAngle = Math.toRadians(getRotationAngle());
				int buildingMapX = ((int)  Math.cos(rotationAngle) * i + (int) Math.sin(rotationAngle) * j) + 1;
				int buildingMapY = ((int) -Math.sin(rotationAngle) * i + (int) Math.cos(rotationAngle) * j) + 1;
				rotatedBuildingMap[i+1][j+1] = buildingMap[buildingMapX][buildingMapY];
			}
		}
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
	
	private double getRotationAngle() {
		switch(orientation) {
		case east:
			return 90;
		case north:
			return 0;
		case south:
			return 180;
		case west:
			return 270;
		default:
			return 0;
		}
	}

	@Override
	public void destroy() {
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
		gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "back");
	}

}
