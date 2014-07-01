package openrr.world.creatures.rockRaider;

import openrr.ai.TaskType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.properties.TaskExecutor;

public class RockRaiderTaskExecutor extends TaskExecutor {
	private static final TaskType[] assignableTaskTypes = new TaskType[]{TaskType.COLLECT_CHRYSTAL, TaskType.COLLECT_ORE};

	public RockRaiderTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.ROCK_RAIDER_TASK_EXECUTOR, assignableTaskTypes);
	}

}
