package openrr.map;

import orre.geom.Dimension2D;
import orre.geom.Vector3D;
import orre.util.ArrayUtils;

public class MapCoordinateUtils {
	private static final float wallHeight = 1.1f;
	private static final float tileSide = 1;
	
	public static Vector3D calculateTileCoordinate(boolean[][] wallMap, MapTile[][] tileMap, Dimension2D mapSize, int x, int y) {
		int xCoord = x;
		int yCoord = y;
		
		if(xCoord == mapSize.width)  xCoord -= 1;
		if(yCoord == mapSize.height) yCoord -= 1;
		
		MapTile mapTile = tileMap[xCoord][yCoord];
		double tileHeight = calculateTileEdgeHeight(wallMap, xCoord, yCoord, mapTile);
		Vector3D coordinate = new Vector3D(
				(float) (((float)x * tileSide) - (tileSide / 2d)), 
				(float) (((float)y * tileSide) - (tileSide / 2d)), 
				(float) tileHeight
			);
		return coordinate;
	}
	
	private static double calculateTileEdgeHeight(boolean[][] wallMap, int x, int y, MapTile mapTile) {
		double vertexWallHeight = wallMap[x][y] ? wallHeight : 0;
		return mapTile.tileHeight[0][0] + vertexWallHeight;
	}
	
	public static Vector3D[] generateCornerVertices(int x, int y, Vector3D[][] mapVertices) {
		Vector3D[] cornerVertices = new Vector3D[4];
		cornerVertices[0] = mapVertices[x][y];
		cornerVertices[1] = mapVertices[x+1][y];
		cornerVertices[2] = mapVertices[x+1][y+1];
		cornerVertices[3] = mapVertices[x][y+1];
		return cornerVertices;
	}
	
	public static void rotateCornerVertices(Vector3D[] cornerVertices, Orientation orientation) {
		switch(orientation) {
			default:
			case north: 
				return; //leave vertex order intact
			case west:
				ArrayUtils.shiftLeft(cornerVertices, 1);
				return;
			case south:
				ArrayUtils.shiftLeft(cornerVertices, 2);
				return;
			case east: 
				ArrayUtils.shiftLeft(cornerVertices, 3);
				return;
		}
	}
}
