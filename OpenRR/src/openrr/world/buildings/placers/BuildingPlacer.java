package openrr.world.buildings.placers;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.Orientation;
import openrr.map.world.MapTileReader;
import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.sceneGraph.SceneNode;


public abstract class BuildingPlacer extends Property {

	private PlaceVisualiser placerAppearance;
	private final ORRGameObjectType buildingType;
	private MapTileReader reader;
	private final TileContents[][] buildingMap;
	private int mouseProbeID;
	
	public BuildingPlacer(Enum<?> type, GameObject gameObject, TileContents[][] buildingMap, ORRGameObjectType building) {
		super(type, gameObject);
		this.buildingType = building;
		this.buildingMap = buildingMap;
	}
	
	@Override
	public void handleMessage(Message<?> message) {
		//this.placeBuilding();
	}

	private void placeBuilding() {
		int gameObjectID = gameObject.world.spawnGameObject(buildingType);
		//start teleport animation
		//update map
	}

	@Override
	public void tick() {
		//1. get mouse map coordinates
		Vector3f mouseLocation = (Vector3f) gameObject.world.requestPropertyData(mouseProbeID, ORRPropertyDataType.MOUSE_LOCATION, null, Vector3f.class);
		//2. update orientation and location
		placerAppearance.updatePosition((int) (mouseLocation.x - 0.5), (int) (mouseLocation.y - 0.5), Orientation.north);
		//3. verify possibility to place building here
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		SceneNode mapNode = MapWorldUtils.getMapRoot(gameObject.world);
		this.mouseProbeID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MOUSE_TRACKER);
		int mapID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		this.placerAppearance = new PlaceVisualiser(buildingMap, reader);
		mapNode.addChild(placerAppearance);
		gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "select");
	}

}
