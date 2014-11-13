package openrr.ai.tasks.core;

import openrr.world.core.AssignableTaskTypes;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class RockRaiderTaskExecutor extends RRTaskExecutor {
	public RockRaiderTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.ROCK_RAIDER_TASK_EXECUTOR, AssignableTaskTypes.ROCK_RAIDER.assignableTypes);
	}
}