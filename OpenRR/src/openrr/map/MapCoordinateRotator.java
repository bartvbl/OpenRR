package openrr.map;

import openrr.map.loader.SubTextureCoordinate;
import orre.geom.Vector3D;
import orre.geom.Vertex3D;
import orre.util.ArrayUtils;

public class MapCoordinateRotator {
	private static Vertex3D[] vertices = new Vertex3D[6];
	
	public static Vertex3D[] generateRotatedTileCoordinates(int x, int y, Vector3D[][] mapVertices, SubTextureCoordinate textureCoordinate, Orientation orientation) {
		Vector3D[] cornerVertices = generateCornerVertices(x, y, mapVertices);
		rotateCornerVertices(cornerVertices, orientation);
		Vector3D[] normals = calculateNormals(cornerVertices);
		createVertices(cornerVertices, textureCoordinate, normals);
		return vertices;
	}

	private static void createVertices(Vector3D[] cornerVertices, SubTextureCoordinate rotatedTextureCoordinates, Vector3D[] normals) {
		// Smooth out the common edges
		Vector3D smoothed = normals[0].plus(normals[1]).normalize();
		
		vertices[0] = createVertex(cornerVertices[0], rotatedTextureCoordinates.u1, rotatedTextureCoordinates.v1, normals[0]);
		vertices[1] = createVertex(cornerVertices[1], rotatedTextureCoordinates.u2, rotatedTextureCoordinates.v1, smoothed);
		vertices[2] = createVertex(cornerVertices[3], rotatedTextureCoordinates.u1, rotatedTextureCoordinates.v2, smoothed);
		
		vertices[3] = createVertex(cornerVertices[1], rotatedTextureCoordinates.u2, rotatedTextureCoordinates.v1, smoothed);
		vertices[4] = createVertex(cornerVertices[2], rotatedTextureCoordinates.u2, rotatedTextureCoordinates.v2, normals[1]);
		vertices[5] = createVertex(cornerVertices[3], rotatedTextureCoordinates.u1, rotatedTextureCoordinates.v2, smoothed);
	}

	private static Vertex3D createVertex(Vector3D coordinate, float textureU, float textureV, Vector3D normal) {
		return new Vertex3D(coordinate.x, coordinate.y, coordinate.z, textureU, textureV, normal.x, normal.y, normal.z);
	}

	private static Vector3D[] calculateNormals(Vector3D[] vertices) {
		Vector3D bottomLeft = vertices[0];
		Vector3D bottomRight = vertices[1];
		Vector3D topLeft = vertices[3];
		Vector3D topRight = vertices[2];
		
		Vector3D normal1Edge1 = topLeft.minus(bottomLeft);
		Vector3D normal1Edge2 = bottomRight.minus(bottomLeft);
		Vector3D normal2Edge1 = topLeft.minus(topRight);
		Vector3D normal2Edge2 = bottomRight.minus(topRight);
		
		Vector3D[] normals = new Vector3D[2];
		
		normals[0] = normal1Edge2.vectorProduct(normal1Edge1).normalize();
		normals[1] = normal2Edge1.vectorProduct(normal2Edge2).normalize();
		
		return normals;
	}

	private static Vector3D[] generateCornerVertices(int x, int y, Vector3D[][] mapVertices) {
		Vector3D[] cornerVertices = new Vector3D[4];
		cornerVertices[0] = mapVertices[x][y];
		cornerVertices[1] = mapVertices[x+1][y];
		cornerVertices[2] = mapVertices[x+1][y+1];
		cornerVertices[3] = mapVertices[x][y+1];
		return cornerVertices;
	}

	private static void rotateCornerVertices(Vector3D[] cornerVertices, Orientation orientation) {
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
