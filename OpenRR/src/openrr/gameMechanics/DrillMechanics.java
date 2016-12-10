package openrr.gameMechanics;

import openrr.map.MapTile;
import openrr.map.soil.SoilType;
import openrr.map.world.MapTileReader;
import openrr.map.world.events.BatchTileUpdate;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRMessageType;
import openrr.world.core.ORRPropertyDataType;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.geom.Point2D;

public class DrillMechanics {
	// Should be sufficiently large to avoid out of bounds errors by queries made by wall collapses.
	private static final int maxWallCollapseRange = 4;

	public static void destroyWall(GameWorld world, Point2D location) {
		int mapID = world.getOnlyGameObject(ORRGameObjectType.MAP);
		BatchTileUpdate updater = new BatchTileUpdate();
		
		MapTileReader tileReader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		
		boolean[][] wallMap = generateWallMap(tileReader, location);
		
		destroyWall(location, 0, 0, tileReader, updater, wallMap);
		
		world.dispatchMessage(new Message<BatchTileUpdate>(ORRMessageType.BATCH_TILE_UPDATE, updater), mapID);
	}

	private static boolean[][] generateWallMap(MapTileReader tileReader, Point2D location) {
		boolean[][] wallMap = new boolean[2 * maxWallCollapseRange + 1][2 * maxWallCollapseRange + 1];
		
		for(int i = -maxWallCollapseRange; i <= maxWallCollapseRange; i++) {
			for(int j = -maxWallCollapseRange; j <= maxWallCollapseRange; j++) {
				wallMap[i + maxWallCollapseRange][j + maxWallCollapseRange] = tileReader.getTileAt(location.x + i, location.y + j).isWall();
			}
		}
		return wallMap;
	}

	private static void destroyWall(Point2D location, int offsetX, int offsetY, MapTileReader tileReader, BatchTileUpdate updater, boolean[][] wallMap) {
		
		
		MapTile existingTile = tileReader.getTileAt(location.x + offsetX, location.y + offsetY);
		
		SoilType rubble = SoilType.RUBBLE_STEP1;
		
		// New tile is a floor tile and rubble.
		MapTile newTile = new MapTile(false, rubble, existingTile.tileHeight.clone());
		
		updater.enqueueTileUpdate(newTile, new Point2D(location.x + offsetX, location.y + offsetY));
		
		// Register that this wall is now gone.. Since the map does not update until we submit the batch map update.
		wallMap[maxWallCollapseRange + offsetX][maxWallCollapseRange + offsetY] = false;
		
		// Update walls around here
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {				
				if(i == 0 && j == 0) {
					continue;
				}
				if(isSingleWidthWall(location, offsetX + i, offsetY + j, wallMap)) {
					destroyWall(location, offsetX + i, offsetY + j, tileReader, updater, wallMap);
				}
			}
		}
	}

	private static boolean isSingleWidthWall(Point2D location, int offsetX, int offsetY, boolean[][] wallMap) {
		int x = offsetX + maxWallCollapseRange;
		int y = offsetY + maxWallCollapseRange;
		
		// If it's not a wall, get lost.
		if(!wallMap[x][y]) {
			return false;
		}
		
		// Check horizontal axis
		if(!wallMap[x - 1][y] && !wallMap[x + 1][y]) {
			return true;
		}
		
		// Check vertical axis
		if(!wallMap[x][y - 1] && !wallMap[x][y + 1]) {
			return true;
		}
		
		// Otherwise, it is not a single width wall
		return false;
	}

}
