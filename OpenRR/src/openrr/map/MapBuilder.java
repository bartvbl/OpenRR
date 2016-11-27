package openrr.map;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import org.lwjgl.BufferUtils;

import openrr.map.loader.MapTexturePack;
import openrr.map.loader.SubTextureCoordinate;
import openrr.map.soil.SoilType;
import orre.geom.Dimension2D;
import orre.geom.Vector3D;
import orre.geom.Vertex3D;
import orre.gl.materials.Material;
import orre.gl.vao.GeometryBufferGenerator;
import orre.gl.vao.GeometryNode;
import orre.gl.vao.VBOFormat;
import orre.sceneGraph.ContainerNode;
import orre.sceneGraph.SceneNode;

public class MapBuilder {
	private static final int verticesPerTile = 6;
	private static final int doublesPerVertex = 3 + 2 + 3; //xyz coordinate + uv texture coordinate + xyz normal coordinate
	private static final float wallHeight = 1.1f;
	private static final float tileSide = 1;

	public static SceneNode buildMapGeometry(MapTile[][] tileMap, MapTexturePack texturePack) {
		int mapWidth = tileMap.length;
		int mapHeight = tileMap[0].length;
		Dimension2D mapSize = new Dimension2D(mapWidth, mapHeight);
		//wallmap size: mapWidth + 1, mapHeight + 1
		boolean[][] wallMap = IsWallMapBuilder.buildIsWallMap(tileMap, mapSize);
		//wallTypeMap size: mapWidth, mapHeight
		WallType[][] wallTypeMap = WallTypeMapBuilder.buildWallTypeMap(wallMap, mapSize);
		//orientationMap size: mapWidth, mapHeight
		Orientation[][] orientationMap = MapWallOrientationBuilder.buildOrientationMap(wallMap, wallTypeMap, mapSize);
		
		SceneNode mapRootNode = generateMapSceneNode(wallMap, wallTypeMap, orientationMap, tileMap, mapSize, texturePack);
		return mapRootNode;
	}

	private static SceneNode generateMapSceneNode(boolean[][] wallMap, WallType[][] wallTypeMap, Orientation[][] orientationMap, MapTile[][] tileMap, Dimension2D mapSize, MapTexturePack texturePack) {
		SceneNode rootNode = new ContainerNode();
		
		//bind a first default texture
		texturePack.bindTexture(SoilType.DIRT, WallType.ground);
		
		int numVertices = (mapSize.width) * (mapSize.height);
		int geometryBufferSize = numVertices * verticesPerTile * doublesPerVertex;
		
		FloatBuffer geometryDataBuffer = BufferUtils.createFloatBuffer(geometryBufferSize);
		
		Vector3D[][] mapVertices = calculateMapVertices(wallMap, tileMap, mapSize);
		//0 index is for the triangle that intersects with 0, 0.5. 1 for the other.

		//getNormalAtVertex(x, y, mapNormals, orientation);
		
		for(int x = 0; x < mapSize.width; x++) {
			for(int y = 0; y < mapSize.height; y++) {
				MapTile mapTile = tileMap[x][y];
				SoilType tileSoilType = mapTile.getSoilType();
				WallType tileWallType = wallTypeMap[x][y];
				Orientation orientation = orientationMap[x][y];
				
				if(!texturePack.isBound(tileSoilType, tileWallType)) {
					if(!(geometryDataBuffer.position() == 0)) {
						compileGeometryBuffer(texturePack, rootNode, geometryDataBuffer);
					}
				}
				texturePack.bindTexture(tileSoilType, tileWallType);
				
				SubTextureCoordinate textureCoordinate = texturePack.getTextureCoordinates();
				Vertex3D[] vertices = MapCoordinateRotator.generateRotatedTileCoordinates(x, y, mapVertices, textureCoordinate, orientation);
				
				putVertices(vertices, geometryDataBuffer);
			}
		}
		
		compileGeometryBuffer(texturePack, rootNode, geometryDataBuffer);
		return rootNode;
	}

	private static Vector3D[][] calculateMapVertices(boolean[][] wallMap, MapTile[][] tileMap, Dimension2D mapSize) {
		Vector3D[][] mapVertices = new Vector3D[mapSize.width + 1][mapSize.height + 1];
		for(int x = 0; x < mapSize.width + 1; x++) {
			for(int y = 0; y < mapSize.height + 1; y++) {
				int xCoord = x;
				int yCoord = y;
				
				if(xCoord == mapSize.width)  xCoord -= 1;
				if(yCoord == mapSize.height) yCoord -= 1;
				
				MapTile mapTile = tileMap[xCoord][yCoord];
				double tileHeight = calculateTileEdgeHeight(wallMap, xCoord, yCoord, mapTile);
				mapVertices[x][y] = new Vector3D(
						(float) (((float)x * tileSide) - (tileSide / 2d)), 
						(float) (((float)y * tileSide) - (tileSide / 2d)), 
						(float) tileHeight
					);
			}
		}
		return mapVertices;
	}
	
	private static void putVertices(Vertex3D[] vertices, FloatBuffer geometryDataBuffer) {
		for(Vertex3D vertex : vertices) {
			float[] vertexData = vertex.toArray();
			geometryDataBuffer.put(vertexData);
		}
	}

	private static double calculateTileEdgeHeight(boolean[][] wallMap, int x, int y, MapTile mapTile) {
		double vertexWallHeight = wallMap[x][y] ? wallHeight : 0;
		return mapTile.tileHeight[0][0] + vertexWallHeight;
	}

	private static void compileGeometryBuffer(MapTexturePack texturePack, SceneNode rootNode, FloatBuffer geometryDataBuffer) {
		Material currentMaterial = texturePack.generateBoundTextureMaterial();
		int vertexCount = geometryDataBuffer.position() / 8;
		IntBuffer indices = generateIndexBuffer(vertexCount);
		GeometryNode buffer = GeometryBufferGenerator.generateGeometryBuffer(VBOFormat.VERTICES_TEXTURES_NORMALS, geometryDataBuffer, indices, vertexCount, vertexCount);
//		GeometryNode normals = GeometryBufferGenerator.generateNormalsGeometryBuffer(VBOFormat.VERTICES_TEXTURES_NORMALS, geometryDataBuffer, indices);
		currentMaterial.addChild(buffer);
//		currentMaterial.addChild(normals);
		rootNode.addChild(currentMaterial);
	}

	private static IntBuffer generateIndexBuffer(int numVertices) {
		IntBuffer indices = BufferUtils.createIntBuffer(numVertices);
		for(int i = 0; i < numVertices; i++) {
			indices.put(i);
		}
		return indices;
	}
	
}