package openrr.world.buildings.placers;

import static openrr.world.buildings.placers.TileContents.BUILDING;
import static openrr.world.buildings.placers.TileContents.EMPTY;
import static openrr.world.buildings.placers.TileContents.POWER_PATH;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class OreRefineryPlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, POWER_PATH, EMPTY}
	};

	public OreRefineryPlacer(GameObject gameObject) {
		super(ORRPropertyType.ORE_REFINERY_PLACER, gameObject, buildingShape, ORRGameObjectType.ORE_REFINERY);
	}
}