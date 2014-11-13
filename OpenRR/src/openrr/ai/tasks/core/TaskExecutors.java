package openrr.ai.tasks.core;

import openrr.world.core.AssignableTaskTypes;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class TaskExecutors {
	public class ToolStoreTaskExecutor extends RRTaskExecutor {
		public ToolStoreTaskExecutor(GameObject gameObject) {
			super(gameObject, ORRPropertyType.TOOL_STORE_TASK_EXECUTOR, AssignableTaskTypes.TOOL_STORE.assignableTypes);
		}
	}
	
	public class RockRaiderTaskExecutor extends RRTaskExecutor {
		public RockRaiderTaskExecutor(GameObject gameObject) {
			super(gameObject, ORRPropertyType.ROCK_RAIDER_TASK_EXECUTOR, AssignableTaskTypes.ROCK_RAIDER.assignableTypes);
		}
	}
}
