package openrr.world.creatures.monster;

import openrr.ai.TaskType;
import openrr.ai.tasks.core.RRTaskExecutor;
import openrr.world.core.ORRPropertyType;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.properties.TaskExecutor;

public class MonsterTaskExecutor extends RRTaskExecutor {
	private static final TaskType[] assignableTaskTypes = new TaskType[]{TaskType.DESTROY_POWER_PATH};

	public MonsterTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.MONSTER_TASK_EXECUTOR, assignableTaskTypes);
	}

}
