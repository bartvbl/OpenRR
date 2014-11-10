package openrr.ai.taskRequests;

import orre.ai.tasks.TaskRequest;
import orre.geom.Point2D;

public class MapTaskRequest extends TaskRequest {

	public final Point2D locationOnMap;

	public MapTaskRequest(int id, Enum<?>[] assignableTaskTypes, Point2D locationOnMap) {
		super(id, assignableTaskTypes);
		//NOTE: ID is always the object that will _execute_ the task. Location on map is where the execution should start from.
		this.locationOnMap = locationOnMap;
	}

}
