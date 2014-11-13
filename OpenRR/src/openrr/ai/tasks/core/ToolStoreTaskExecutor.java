package openrr.ai.tasks.core;

import openrr.world.core.AssignableTaskTypes;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;

public class ToolStoreTaskExecutor extends RRTaskExecutor {
	public ToolStoreTaskExecutor(GameObject gameObject) {
		super(gameObject, ORRPropertyType.TOOL_STORE_TASK_EXECUTOR, AssignableTaskTypes.TOOL_STORE.assignableTypes);
	}
}