package openrr.world.buildings.placers;

import static openrr.world.buildings.placers.TileContents.BUILDING;
import static openrr.world.buildings.placers.TileContents.EMPTY;
import static openrr.world.buildings.placers.TileContents.POWER_PATH;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class SupportStationPlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, EMPTY,	 	EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, POWER_PATH, EMPTY}
	};

	public SupportStationPlacer(GameObject gameObject) {
		super(ORRPropertyType.SUPPORT_STATION_PLACER, gameObject, buildingShape, ORRGameObjectType.SUPPORT_STATION);
	}
}
