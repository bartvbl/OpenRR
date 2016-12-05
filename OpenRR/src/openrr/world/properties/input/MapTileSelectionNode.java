package openrr.world.properties.input;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import openrr.map.MapTile;
import openrr.map.world.MapTileReader;
import orre.geom.Vertex3D;
import orre.gl.renderer.RenderState;
import orre.gl.renderer.ShaderProperty;
import orre.gl.util.OneShotDrawer;
import orre.gl.vao.VBOFormat;
import orre.sceneGraph.ContainerNode;

public class MapTileSelectionNode extends ContainerNode {

	private int tileX;
	private int tileY;
	private MapTile selectedTile;
	private boolean isEnabled = false;

	public MapTileSelectionNode(MapTileReader reader) {
		this.tileReader = reader;
	}

	public void update(int selectionX, int selectionY, MapTile tile) {
		this.tileX = selectionX;
		this.tileY = selectionY;
		this.selectedTile = tile;
		isEnabled = true;
	}
	
	//6 vertices * 3 values/vertex = 12
	private final float[] vertexData = new float[18];
	
	// Different ordering due to peculiar vertex ordering in map construction
	private static final int[] indexData = new int[]{
		0, 1, 2, 3, 4, 5
	};
	
	private static final float selectionHeight = 0.01f;
	private static final float[] selectionColour = new float[]{0.2f, 0.2f, 0.8f, 0.7f};
	private static final float[] black = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
	private static final float[] transparent_black = new float[]{0.0f, 0.0f, 0.0f, selectionColour[3]};
	private static final float[] white = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
	
	private final MapTileReader tileReader;

	
	private FloatBuffer materialBuffer = BufferUtils.createFloatBuffer(4);
	
	@Override
	public void render(RenderState state) {
		if(isEnabled) {
			drawTile(state, tileX, tileY);
		}
	}

	private void drawTile(RenderState state, int x, int y) {
		MapTile tile = tileReader.getTileAt(x, y);
		
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_AMBIENT, selectionColour);
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_DIFFUSE, transparent_black);
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_SPECULAR, black);
		state.shaders.setProperty4f(ShaderProperty.MATERIAL_EMISSION, black);
		
		storeVertices(tile);
		OneShotDrawer.drawTriangles(state, vertexData, indexData, VBOFormat.VERTICES);
	}

	private void storeVertices(MapTile tile) {
		Vertex3D[] vertices = tile.getVertices();
		for(int i = 0; i < 6; i++){
			vertexData[(3 * i) + 0] = vertices[i].x;
			vertexData[(3 * i) + 1] = vertices[i].y;
			vertexData[(3 * i) + 2] = vertices[i].z + selectionHeight;
		}
	}

	public void hide() {
		this.isEnabled = false;
	}

}
