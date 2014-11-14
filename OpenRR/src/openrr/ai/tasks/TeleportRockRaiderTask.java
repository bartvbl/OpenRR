package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.AnimationAction;
import openrr.ai.actions.TeleportAction;
import openrr.world.core.ORRGameObjectType;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameWorld;
import orre.sceneGraph.CoordinateNode;

public class TeleportRockRaiderTask extends ORRTask {

	public TeleportRockRaiderTask() {
		super(TaskType.TELEPORT_ROCK_RAIDER, -1);
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster, GameWorld world) {
		TeleportAction teleport = TeleportAction.plan(request, world);
		
		return new Assignment(new Task[]{this}, new Plan(new Action[]{teleport}));
	}

}
