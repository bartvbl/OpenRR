package openrr.world.buildings.placers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import openrr.map.MapTile;
import openrr.map.Orientation;
import openrr.map.world.MapTileReader;
import orre.gl.vertexArrays.VertexArrayDrawer;
import orre.sceneGraph.LeafNode;
import static org.lwjgl.opengl.GL11.*;

public class PlaceVisualiser extends LeafNode {
	
	private static final double cubeHeight = 0.08;
	
	//8 vertices * 3 values/vertex = 24
	private static final double[] vertexData = new double[24];
	
	private static final int[] indexData = new int[]{
		1, 2, 3, 0, 2, 1,
		5, 4, 0, 5, 0, 1,
		7, 5, 1, 7, 1, 3,
		6, 3, 2, 3, 6, 7,
		6, 2, 0, 6, 0, 4
	};

	private static final float[] placableBuildingTileColour = new float[]{0.0f, 1.0f, 0.0f, 0.3f};
	private static final float[] placablePowerTileColour = new float[]{0.3f, 1.0f, 0.0f, 0.3f};
	private static final float[] notPlacableTileColour = new float[]{1.0f, 0.0f, 0.0f, 0.3f};
	
	private final TileContents[][] buildingMap;
	private final MapTileReader tileReader;

	private int tileX;
	private int tileY;
	private Orientation orientation;
	
	private FloatBuffer materialBuffer = BufferUtils.createFloatBuffer(4);
	
	public PlaceVisualiser(TileContents[][] buildingMap, MapTileReader reader) {
		this.buildingMap = buildingMap;
		this.tileReader = reader;
	}
	
	@Override
	public void render() {
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				double rotationAngle = getRotationAngle();
				int buildingMapX = ((int)  Math.cos(rotationAngle) * i + (int) Math.sin(rotationAngle) * j) + 1;
				int buildingMapY = ((int) -Math.sin(rotationAngle) * i + (int) Math.cos(rotationAngle) * j) + 1;
				if(buildingMap[buildingMapX][buildingMapY] == TileContents.BUILDING) {
					drawTile(tileX + i, tileY + j, placableBuildingTileColour);
				} else if(buildingMap[buildingMapX][buildingMapY] == TileContents.POWER_PATH) {
					drawTile(tileX + i, tileY + j, placablePowerTileColour);
				}
			}
		}
	}

	private double getRotationAngle() {
		switch(orientation) {
		case east:
			return Math.PI / 2d;
		case north:
			return 0;
		case south:
			return Math.PI;
		case west:
			return (3d * Math.PI) / 2d;
		default:
			return 0;
		
		}
	}

	private void drawTile(int x, int y, float[] tileColour) {
		MapTile tile = tileReader.getTileAt(x, y);
		materialBuffer.put(tileColour).rewind();
		glMaterial(GL_FRONT, GL_DIFFUSE, materialBuffer);
		materialBuffer.put(tileColour).rewind();
		glMaterial(GL_FRONT, GL_SPECULAR, materialBuffer);
		int arrayPointer = 0;
		arrayPointer = storeVertices(x-1, y-1, tile, arrayPointer, cubeHeight);
		storeVertices(x-1, y-1, tile, arrayPointer, 0);
		VertexArrayDrawer.drawTriangles(vertexData, indexData, 3, 4);
	}

	private int storeVertices(int x, int y, MapTile tile, int arrayPointer, double heightAboveGround) {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				vertexData[arrayPointer + 0] = (double) (x + i) + 0.5;
				vertexData[arrayPointer + 1] = (double) (y + j) + 0.5;
				vertexData[arrayPointer + 2] = tile.tileHeight[i][j] + heightAboveGround;
				arrayPointer += 3;
			}
		}
		return arrayPointer;
	}

	public void updatePosition(int tileX, int tileY, Orientation orientation) {
		this.tileX = tileX;
		this.tileY = tileY;
		this.orientation = orientation;
	}

}
