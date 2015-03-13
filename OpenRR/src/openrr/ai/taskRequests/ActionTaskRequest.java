package openrr.ai.taskRequests;

import orre.ai.tasks.TaskRequest;

public class ActionTaskRequest extends TaskRequest {

	public ActionTaskRequest(int id, Enum<?>[] assignableTaskTypes) {
		super(id, assignableTaskTypes);
	}

}
