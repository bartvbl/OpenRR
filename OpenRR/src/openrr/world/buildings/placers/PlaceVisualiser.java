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
	
	private static final double cubeHeight = 0.1d;
	
	//5 quads per tile 'cube' * 4 points/quad * 3 values/point = 60
	private static final double[] vertexData = new double[12];
	//order: 0-3 on top, starting bottom left, counter clockwise. Same for 4-7 on the bottom layer.
	private static final int[] indexData = new int[]{
		0, 1, 2, 0, 2, 3
		//0, 4, 5, 1,
		//1, 5, 6, 2,
		//2, 6, 7, 3,
		//3, 7, 4, 0
	};

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
				drawTile(tileX + i, tileY + j);
			}
		}
	}

	private void drawTile(int x, int y) {
		MapTile tile = tileReader.getTileAt(x+1, y+1);
		float[] colour = new float[]{0.0f, 1.0f, 0.0f, 0.3f};
		materialBuffer.put(colour).rewind();
		glMaterial(GL_FRONT, GL_DIFFUSE, materialBuffer);
		materialBuffer.put(colour).rewind();
		glMaterial(GL_FRONT, GL_SPECULAR, materialBuffer);
		int arrayPointer = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				vertexData[arrayPointer + 0] = (double) (x + i) + 0.5;
				vertexData[arrayPointer + 1] = (double) (y + j) + 0.5;
				vertexData[arrayPointer + 2] = tile.tileHeight[i][j] + cubeHeight;
				arrayPointer += 3;
			}
		}
		VertexArrayDrawer.drawTriangles(vertexData, indexData, 3, 4);
	}

	public void updatePosition(int tileX, int tileY, Orientation orientation) {
		this.tileX = tileX;
		this.tileY = tileY;
		this.orientation = orientation;
	}

}
