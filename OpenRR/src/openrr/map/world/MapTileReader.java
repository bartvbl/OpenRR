package openrr.map.world;

import orre.util.MathUtil;
import openrr.map.MapTile;

public class MapTileReader {
	private final MapTile[][] tileMap;

	public MapTileReader(MapTile[][] tileMap) {
		this.tileMap = tileMap;
	}
	
	public MapTile getTileAt(int x, int y) {
		x = MathUtil.clamp(x, 0, tileMap.length - 1);
		y = MathUtil.clamp(y, 0, tileMap[0].length - 1);
		return tileMap[x][y];
	}
	
	public double getTileHeightAt(double x, double y) {
		MapTile tile = getTileAt((int)x, (int)y);
		double xOnTile = x - Math.floor(x);
		double yOnTile = y - Math.floor(y);
		if(x > y) {
			double dx = tile.tileHeight[1][0] - tile.tileHeight[0][0];
			double dy = tile.tileHeight[1][1] - tile.tileHeight[1][0];
			return tile.tileHeight[0][0] + xOnTile*dx + yOnTile*dy;
		} else {
			double dx = tile.tileHeight[1][1] - tile.tileHeight[0][1];
			double dy = tile.tileHeight[0][1] - tile.tileHeight[0][0];
			return tile.tileHeight[0][0] + xOnTile*dx + yOnTile*dy;
		}
	}
}
