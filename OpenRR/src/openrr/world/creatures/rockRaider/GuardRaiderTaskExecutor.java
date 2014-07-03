package openrr.world.creatures.rockRaider;

import openrr.ai.TaskType;
import openrr.ai.tasks.core.RRTaskExecutor;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.properties.TaskExecutor;

public class GuardRaiderTaskExecutor extends RRTaskExecutor {
	private static final TaskType[] assignableTaskTypes = new TaskType[]{TaskType.COLLECT_CHRYSTAL, TaskType.COLLECT_ORE};

	public GuardRaiderTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.ROCK_RAIDER_TASK_EXECUTOR, assignableTaskTypes);
	}

}
