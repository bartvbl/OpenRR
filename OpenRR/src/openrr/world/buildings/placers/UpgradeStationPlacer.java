package openrr.world.buildings.placers;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import static openrr.world.buildings.placers.TileContents.*;

public class UpgradeStationPlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, EMPTY, 		EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, POWER_PATH, EMPTY}
	};

	public UpgradeStationPlacer(GameObject gameObject) {
		super(ORRPropertyType.UPGRADE_STATION_PLACER, gameObject, buildingShape, ORRGameObjectType.UPGRADE_STATION);
	}
}
