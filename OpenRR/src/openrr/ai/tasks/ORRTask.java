package openrr.ai.tasks;

import orre.ai.tasks.Task;
import orre.ai.tasks.TaskRequest;
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
	
}
