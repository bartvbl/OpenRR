package openrr.camera;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import orre.gl.util.TransformMatrixUtils;
import orre.rendering.RenderState;
import orre.sceneGraph.Camera;

public class MapCamera extends Camera {
	private static final Vector3f xAxis = new Vector3f(1, 0, 0);
	private static final Vector3f yAxis = new Vector3f(0, 1, 0);
	private static final Vector3f zAxis = new Vector3f(0, 0, 1);
		
	private final Vector3f rotation = new Vector3f(0, 0, 0);
	private final Vector3f location = new Vector3f(0, 0, 30);
	
	private double mapHeight;
	
	public void rotate(double rotationX, double rotationY, double rotationZ) {
		rotation.translate((float)rotationX, (float)rotationY, (float)rotationZ);
	}
	
	public void translate(double x, double y, double z) {
		location.translate((float) x, (float) y, (float) z);
	}
	
	public void setMapHeight(double mapHeight) {
		this.mapHeight = mapHeight;
	}
	
	@Override
	public void transform(RenderState state) {
		transformationMatrix.setIdentity();
		transformationMatrix.translate(new Vector3f(location.x, location.y, 0));
		transformationMatrix.rotate((float) Math.toRadians(rotation.z), zAxis);
		
		transformationMatrix.rotate((float) Math.toRadians(rotation.y), yAxis);
		transformationMatrix.translate(new Vector3f(0, 0, (float) mapHeight));
		transformationMatrix.rotate((float) Math.toRadians(rotation.x), xAxis);
		
		transformationMatrix.translate(new Vector3f(0, 0, (location.z)));
		Matrix4f inverse = new Matrix4f();
		Matrix4f.invert(transformationMatrix, inverse);
		TransformMatrixUtils.applyMatrixOnCurrentMatrix(inverse);
	}

	public double getX() {
		return this.location.x;
	}

	public double getY() {
		return this.location.y;
	}
	
	public double getRotationZ() {
		return this.rotation.z;
	}
	
	public String toString() {
		return "Map Camera";
	}
}
