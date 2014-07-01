package openrr.world.creatures.monster;

import openrr.ai.TaskType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.properties.TaskExecutor;

public class MonsterTaskExecutor extends TaskExecutor {
	private static final TaskType[] assignableTaskTypes = new TaskType[]{TaskType.DESTROY_POWER_PATH};

	public MonsterTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.MONSTER_TASK_EXECUTOR, assignableTaskTypes);
	}

}
