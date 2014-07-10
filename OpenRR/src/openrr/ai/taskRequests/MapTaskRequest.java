package openrr.ai.taskRequests;

import orre.ai.tasks.TaskRequest;
import orre.geom.Point2D;

public class MapTaskRequest extends TaskRequest {

	public final Point2D locationOnMap;

	public MapTaskRequest(int id, Enum<?>[] assignableTaskTypes, Point2D locationOnMap) {
		super(id, assignableTaskTypes);
		this.locationOnMap = locationOnMap;
	}

}
