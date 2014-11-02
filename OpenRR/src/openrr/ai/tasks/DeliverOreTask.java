package openrr.ai.tasks;

import openrr.ai.TaskType;
import openrr.ai.actions.DropoffAction;
import openrr.ai.actions.MoveAction;
import openrr.ai.actions.PickupAction;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;

public class DeliverOreTask extends Task {

	private final GameWorld world;

	public DeliverOreTask(int gameObjectID, GameWorld world) {
		super(TaskType.DELIVER_ORE, gameObjectID);
		this.world = world;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster) {
		MoveAction moveToBuildingAction = MoveAction.plan(request, world);
		DropoffAction pickupAction = DropoffAction.plan(request, world);
		Action[] plannedActions = {moveToBuildingAction, pickupAction};
		Task[] completedTasks = {this};
		return new Assignment(completedTasks, new Plan(plannedActions));
	}

}
