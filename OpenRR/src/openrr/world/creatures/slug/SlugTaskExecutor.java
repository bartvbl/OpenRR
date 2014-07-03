package openrr.world.creatures.slug;

import openrr.ai.TaskType;
import openrr.ai.tasks.core.RRTaskExecutor;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.properties.TaskExecutor;

public class SlugTaskExecutor extends RRTaskExecutor {
	private static final TaskType[] assignableTaskTypes = new TaskType[]{TaskType.ATTACK_MONSTER};

	public SlugTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.SLUG_TASK_EXECUTOR, assignableTaskTypes);
	}

}
