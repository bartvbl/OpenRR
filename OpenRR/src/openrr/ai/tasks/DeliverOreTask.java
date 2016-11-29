package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.DropoffAction;
import openrr.ai.actions.PickupAction;
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
import orre.geom.Point2D;

public class DeliverOreTask extends Task {
	private final Point2D buildingLocation;

	public DeliverOreTask(int gameObjectID, Point2D buildingLocation) {
		super(TaskType.DELIVER_ORE, gameObjectID);
		this.buildingLocation = buildingLocation;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster, GameWorld world) {
		if(!(request instanceof MapTaskRequest)) {
			throw new RuntimeException("ORR tasks need to have MapTaskRequests!");
		}
		MapTaskRequest mapRequest = (MapTaskRequest) request;
		MoveAction moveToBuildingAction = MoveAction.plan(request.targetID, mapRequest.locationOnMap, buildingLocation, world, AnimationType.raiderCarrying);
		DropoffAction pickupAction = DropoffAction.plan(request, world);
		Action[] plannedActions = {moveToBuildingAction, pickupAction};
		Task[] completedTasks = {}; //doesn't include deliver ore task, as it can be completed multiple times
		return new Assignment(completedTasks, new Plan(plannedActions));
	}

}
