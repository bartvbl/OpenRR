package openrr.world.buildings.placers;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import static openrr.world.buildings.placers.TileContents.*;

public class ToolStorePlacer extends BuildingPlacer {
	
	private static final TileContents[][] buildingShape = new TileContents[][]{
		{EMPTY, EMPTY, 		EMPTY},
		{EMPTY, BUILDING, 	EMPTY},
		{EMPTY, POWER_PATH, EMPTY}
	};

	public ToolStorePlacer(GameObject gameObject) {
		super(ORRPropertyType.TOOL_STORE_PLACER, gameObject, buildingShape, ORRGameObjectType.TOOL_STORE);
	}
}
