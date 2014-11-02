package openrr.ai.tasks;

import orre.ai.tasks.Task;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point2D;
import orre.geom.mesh.Model;
import openrr.ai.TaskType;
import openrr.ai.taskRequests.MapTaskRequest;

public abstract class ORRTask extends Task {

	public ORRTask(TaskType taskType, int gameObjectID) {
		super(taskType, gameObjectID);
	}
	
	protected MapTaskRequest getMapTaskRequest(TaskRequest request) {
		if(!(request instanceof MapTaskRequest)) {
			throw new RuntimeException("ORRTask requires instance of MapTaskRequest!");
		}
		return (MapTaskRequest) request;
	}
	
	protected Point2D getTargetLocation(MapTaskRequest request, GameWorld world) {
		Model appearance = (Model) world.requestPropertyData(request.targetID, PropertyDataType.APPEARANCE, null, Model.class);
		return new Point2D((int)appearance.getRootNode().getX(), (int) appearance.getRootNode().getY());
	}
	
}
