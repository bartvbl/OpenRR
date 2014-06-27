package openrr.world.buildings.placers;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import static openrr.world.buildings.placers.TileContents.*;

public class DockPlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, WATER, 		EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, POWER_PATH, EMPTY}
	};

	public DockPlacer(GameObject gameObject) {
		super(ORRPropertyType.DOCK_PLACER, gameObject, buildingShape, ORRGameObjectType.DOCKS);
	}
}
