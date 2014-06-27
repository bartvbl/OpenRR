package openrr.world.buildings.placers;

import static openrr.world.buildings.placers.TileContents.BUILDING;
import static openrr.world.buildings.placers.TileContents.EMPTY;
import static openrr.world.buildings.placers.TileContents.POWER_PATH;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class SmallTeleportPlacer extends BuildingPlacer {

	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, EMPTY, 		EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, POWER_PATH, EMPTY}
	};

	public SmallTeleportPlacer(GameObject gameObject) {
		super(ORRPropertyType.SMALL_TELEPORT_PLACER, gameObject, buildingShape, ORRGameObjectType.TELEPORT_PAD);
	}

}
