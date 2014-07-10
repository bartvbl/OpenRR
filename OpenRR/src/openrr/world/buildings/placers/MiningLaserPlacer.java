package openrr.world.buildings.placers;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import static openrr.world.buildings.placers.TileContents.*;

public class MiningLaserPlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, EMPTY, 		EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, EMPTY, EMPTY}
	};

	public MiningLaserPlacer(GameObject gameObject) {
		super(ORRPropertyType.MINING_LASER_PLACER, gameObject, buildingShape, ORRGameObjectType.MINING_LASER);
	}
}
