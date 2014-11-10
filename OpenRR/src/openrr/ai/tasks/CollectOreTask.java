package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.PickupAction;
import openrr.ai.actions.movement.MoveAction;
import openrr.ai.taskRequests.MapTaskRequest;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.geom.Point2D;

public class CollectOreTask extends ORRTask {

	private final Point2D location;
	private final GameWorld world;

	public CollectOreTask(int gameObjectID, Point2D oreLocation, GameWorld world) {
		super(TaskType.COLLECT_ORE, gameObjectID);
		this.location = oreLocation;
		this.world = world;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster) {
		//step 1: move to ore, and pick it up.
		MapTaskRequest mapRequest = getMapTaskRequest(request);
		MoveAction moveToOreAction = MoveAction.plan(request.targetID, mapRequest.locationOnMap, location, world);
		PickupAction pickupAction = PickupAction.plan(request, world);
		Action[] plannedActions = {moveToOreAction, pickupAction};
		Task[] completedTasks = {this};
		Assignment pickupAssignment = new Assignment(completedTasks, new Plan(plannedActions));
		
		//step 2: deliver the ore to some place.
		Assignment deliveryAssignment = taskMaster.findAssignment(new MapTaskRequest(request.targetID, new TaskType[]{TaskType.DELIVER_ORE}, location));
		
		Assignment collectOreAssignment = pickupAssignment.next(deliveryAssignment);
		return collectOreAssignment;
	}
}
