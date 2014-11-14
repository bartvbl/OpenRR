package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.AnimationAction;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameWorld;

public class TeleportRockRaiderTask extends ORRTask {

	public TeleportRockRaiderTask() {
		super(TaskType.TELEPORT_ROCK_RAIDER, -1);
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster, GameWorld world) {
		AnimationAction action = AnimationAction.plan(request, AnimationType.teleportRockRaider, world);
		return new Assignment(new Task[]{this}, new Plan(new Action[]{action}));
	}

}
