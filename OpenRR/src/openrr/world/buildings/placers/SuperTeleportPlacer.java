package openrr.world.buildings.placers;

import static openrr.world.buildings.placers.TileContents.BUILDING;
import static openrr.world.buildings.placers.TileContents.EMPTY;
import static openrr.world.buildings.placers.TileContents.POWER_PATH;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class SuperTeleportPlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, 		EMPTY, 		EMPTY},
		{BUILDING, 		BUILDING, 	EMPTY},
		{POWER_PATH, 	POWER_PATH, EMPTY}
	};

	public SuperTeleportPlacer(GameObject gameObject) {
		super(ORRPropertyType.SUPER_TELEPORT_PLACER, gameObject, buildingShape, ORRGameObjectType.SUPER_TELEPORT);
	}
}
