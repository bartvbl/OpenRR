package openrr.world.buildings.placers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import openrr.map.MapTile;
import openrr.map.Orientation;
import openrr.map.world.MapTileReader;
import orre.gl.util.OneShotDrawer;
import orre.gl.vao.VBOFormat;
import orre.rendering.RenderState;
import orre.rendering.ShaderProperty;
import orre.sceneGraph.LeafNode;
import static org.lwjgl.opengl.GL11.*;

public class PlaceVisualiser extends LeafNode {
	
	private static final double cubeHeight = 0.08;
	
	//8 vertices * 3 values/vertex = 24
	private final float[] vertexData = new float[24];
	
	private static final int[] indexData = new int[]{
		1, 2, 3, 0, 2, 1,
		5, 4, 0, 5, 0, 1,
		7, 5, 1, 7, 1, 3,
		6, 3, 2, 3, 6, 7,
		6, 2, 0, 6, 0, 4
	};

	private static final float[] placableBuildingTileColour = new float[]{0.0f, 1.0f, 0.0f, 0.4f};
	private static final float[] placablePowerTileColour = new float[]{0.3f, 1.0f, 0.0f, 0.4f};
	private static final float[] placableWaterTileColour = new float[]{0.1f, 0.4f, 1.0f, 0.4f};
	private static final float[] notPlacableTileColour = new float[]{1.0f, 0.0f, 0.0f, 0.4f};
	private static final float[] black = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
	private static final float[] white = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
	
	private TileContents[][] buildingMap;
	private final MapTileReader tileReader;

	private int tileX;
	private int tileY;
	
	private FloatBuffer materialBuffer = BufferUtils.createFloatBuffer(4);
	
	public PlaceVisualiser(TileContents[][] buildingMap, MapTileReader reader) {
		this.buildingMap = buildingMap;
		this.tileReader = reader;
	}
	
	@Override
	public void render(RenderState state) {
		state.shaders.setPropertyb(ShaderProperty.TEXTURES_ENABLED, false);
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(buildingMap[i+1][j+1] != TileContents.EMPTY) {
					if(buildingMap[i+1][j+1] == TileContents.BUILDING) {
						drawTile(state, tileX+i, tileY+j, placableBuildingTileColour);
					} else if(buildingMap[i+1][j+1] == TileContents.POWER_PATH) {
						drawTile(state, tileX+i, tileY+j, placablePowerTileColour);
					} else if(buildingMap[i+1][j+1] == TileContents.WATER) {
						drawTile(state, tileX+i, tileY+j, placableWaterTileColour);
					}
				}
			}
		}
	}

	private void drawTile(RenderState state, int x, int y, float[] tileColour) {
		MapTile tile = tileReader.getTileAt(x, y);
		
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_AMBIENT, tileColour);
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_DIFFUSE, tileColour);
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_SPECULAR, tileColour);
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_EMISSION, black);
		
		int arrayPointer = 0;
		arrayPointer = storeVertices(x-1, y-1, tile, arrayPointer, cubeHeight);
		storeVertices(x-1, y-1, tile, arrayPointer, 0);
		OneShotDrawer.drawTriangles(state, vertexData, indexData, VBOFormat.VERTICES);
	}

	private int storeVertices(int x, int y, MapTile tile, int arrayPointer, double heightAboveGround) {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				vertexData[arrayPointer + 0] = (float) (x + i) + 0.5f;
				vertexData[arrayPointer + 1] = (float) (y + j) + 0.5f;
				vertexData[arrayPointer + 2] = (float) (tile.tileHeight[i][j] + heightAboveGround);
				arrayPointer += 3;
			}
		}
		return arrayPointer;
	}

	public void updatePosition(int tileX, int tileY, TileContents[][] rotatedBuildingMap) {
		this.tileX = tileX;
		this.tileY = tileY;
		this.buildingMap = rotatedBuildingMap;
	}

}
