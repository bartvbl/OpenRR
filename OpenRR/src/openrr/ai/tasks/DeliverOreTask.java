package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.DropoffAction;
import openrr.ai.actions.MoveAction;
import openrr.ai.actions.PickupAction;
import openrr.ai.taskRequests.MapTaskRequest;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.geom.Point2D;

public class DeliverOreTask extends Task {

	private final GameWorld world;
	private final Point2D buildingLocation;

	public DeliverOreTask(int gameObjectID, Point2D buildingLocation, GameWorld world) {
		super(TaskType.DELIVER_ORE, gameObjectID);
		this.world = world;
		this.buildingLocation = buildingLocation;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster) {
		if(!(request instanceof MapTaskRequest)) {
			throw new RuntimeException("ORR tasks need to have MapTaskRequests!");
		}
		MapTaskRequest mapRequest = (MapTaskRequest) request;
		MoveAction moveToBuildingAction = MoveAction.plan(mapRequest.locationOnMap, buildingLocation, world);
		DropoffAction pickupAction = DropoffAction.plan(request, world);
		Action[] plannedActions = {moveToBuildingAction, pickupAction};
		Task[] completedTasks = {this};
		return new Assignment(completedTasks, new Plan(plannedActions));
	}

}
