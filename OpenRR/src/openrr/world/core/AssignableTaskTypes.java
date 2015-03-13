package openrr.world.core;

import static openrr.ai.TaskType.*;
import openrr.ai.TaskType;

public enum AssignableTaskTypes {
	TOOL_STORE	(new TaskType[]{TELEPORT_ROCK_RAIDER}),
	ROCK_RAIDER	(new TaskType[]{COLLECT_CHRYSTAL, COLLECT_ORE}),
	;
	
	public final TaskType[] assignableTypes;

	private AssignableTaskTypes(TaskType[] assignableTypes) {
		this.assignableTypes = assignableTypes;
	}
}
