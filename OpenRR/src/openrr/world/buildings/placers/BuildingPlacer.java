package openrr.world.buildings.placers;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRGameObjectType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.sceneGraph.SceneNode;


public abstract class BuildingPlacer extends Property {

	private final PlaceVisualiser placerAppearance;
	private final ORRGameObjectType buildingType;
	
	public BuildingPlacer(Enum<?> type, GameObject gameObject, TileContents[][] buildingMap, ORRGameObjectType building) {
		super(type, gameObject);
		this.placerAppearance = new PlaceVisualiser(buildingMap);
		this.buildingType = building;
	}
	
	@Override
	public void handleMessage(Message<?> message) {
		this.placeBuilding();
	}

	private void placeBuilding() {
		int gameObjectID = gameObject.world.spawnGameObject(buildingType);
		//start teleport animation
		//update map
	}

	@Override
	public void tick() {
		//1. get mouse map coordinates
		//2. update orientation and location
		//3. verify possibility to place building here
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		SceneNode mapNode = MapWorldUtils.getMapRoot(gameObject.world);
		mapNode.addChild(placerAppearance);
		gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "select");
	}

}
