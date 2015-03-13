package openrr.ai.tasks.core;

import openrr.ai.taskRequests.MapTaskRequest;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.PropertyDataType;
import orre.gameWorld.properties.TaskExecutor;
import orre.geom.Point2D;
import orre.geom.Point3D;
import orre.geom.mesh.Model;

public abstract class RRTaskExecutor extends TaskExecutor {

	public RRTaskExecutor(GameObject gameObject, Enum<?> propertyType, Enum<?>[] assignableTaskTypes) {
		super(gameObject, propertyType, assignableTaskTypes);
	}

	@Override
	protected final TaskRequest generateTaskRequest() {
		Model appearance = (Model) gameObject.requestPropertyData(PropertyDataType.APPEARANCE, Model.class);
		Point3D location = appearance.getRootNode().getLocation();
		Point2D location2D = location.in2D();
		return new MapTaskRequest(gameObject.id, this.assignableTaskTypes, location2D);
	}

}
