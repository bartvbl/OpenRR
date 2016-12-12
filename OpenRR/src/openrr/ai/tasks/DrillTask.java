package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.DrillAction;
import openrr.ai.actions.movement.MoveAction;
import openrr.ai.taskRequests.MapTaskRequest;
import openrr.animation.AnimationType;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.geom.Index2D;

public class DrillTask extends ORRTask {

	private final Index2D location;

	public DrillTask(int gameObjectID, Index2D locationOnMap) {
		super(TaskType.DRILL, gameObjectID);
		this.location = locationOnMap;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster, GameWorld world) {
		MapTaskRequest mapRequest = getMapTaskRequest(request);
		MoveAction moveToWallAction = MoveAction.plan(request.targetID, mapRequest.locationOnMap, location.toPoint2D(), world, AnimationType.raiderWalking, 1);
		DrillAction drillAction = DrillAction.plan(location.toPoint2D(), mapRequest, world);
		
		Task[] completedTasks = new Task[]{this};
		
		Action[] actions = new Action[]{moveToWallAction, drillAction};
		Plan plan = new Plan(actions);
		
		return new Assignment(completedTasks, plan);
	}

}
