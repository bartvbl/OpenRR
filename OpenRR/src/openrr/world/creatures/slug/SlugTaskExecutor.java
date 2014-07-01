package openrr.world.creatures.slug;

import openrr.ai.TaskType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.properties.TaskExecutor;

public class SlugTaskExecutor extends TaskExecutor {
	private static final TaskType[] assignableTaskTypes = new TaskType[]{TaskType.SUCK_BUILDING_POWER};

	public SlugTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.SLUG_TASK_EXECUTOR, assignableTaskTypes);
	}

}
