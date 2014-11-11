package openrr.ai.tasks;

import openrr.ai.TaskType;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;

public class TeleportRockRaiderTask extends ORRTask {

	public TeleportRockRaiderTask() {
		super(TaskType.TELEPORT_ROCK_RAIDER, -1);
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster) {
		return null;
	}

}
