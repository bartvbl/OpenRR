package openrr.world.properties.input;

import orre.gl.util.CoordConverter;
import orre.sceneGraph.LeafNode;
import orre.sceneGraph.SceneNode;

public class MouseProbeNode extends LeafNode implements SceneNode {
	private double mouseX;
	private double mouseY;
	
	private float[] mapCoordinates = new float[]{0, 0, 0};
	
	public void setMouseCoordinates(double mouseX, double mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	
	public float[] getMapCoordinates() {
		return mapCoordinates;
	}
	
	@Override
	public void preRender() {
		mapCoordinates = CoordConverter.getMapCoords((int)mouseX, (int)mouseY);
	}
	
	public String toString() {
		return "Map mouse position probe node";
	}
}
