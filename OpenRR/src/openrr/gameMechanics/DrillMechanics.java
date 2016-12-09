package openrr.gameMechanics;

import openrr.map.MapTile;
import openrr.map.soil.SoilType;
import openrr.map.world.MapTileReader;
import openrr.map.world.events.BatchTileUpdate;
import openrr.map.world.events.MapSoilUpdate;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRMessageType;
import openrr.world.core.ORRPropertyDataType;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.geom.Point2D;

public class DrillMechanics {

	public static void destroyWall(GameWorld world, Point2D location) {
		int mapID = world.getOnlyGameObject(ORRGameObjectType.MAP);
		BatchTileUpdate updater = new BatchTileUpdate();
		
		MapTileReader tileReader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {				
				MapTile existingTile = tileReader.getTileAt(location.x, location.y);
				MapTile newTile = new MapTile(false, existingTile.getSoilType(), existingTile.tileHeight.clone());
				
				updater.enqueueTileUpdate(newTile, new Point2D(location.x + i, location.y + j));
			}
		}
		
		
		world.dispatchMessage(new Message<BatchTileUpdate>(ORRMessageType.BATCH_TILE_UPDATE, updater), mapID);
	}

}
