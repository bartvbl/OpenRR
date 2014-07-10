package openrr.ai.tasks;

import openrr.ai.MapTileNode;
import openrr.ai.TaskType;
import openrr.ai.actions.MoveAction;
import openrr.ai.taskRequests.MapTaskRequest;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.pathFinding.Path;
import orre.ai.tasks.Action;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.geom.Point2D;

public class CollectOreTask extends Task {

	private final Point2D location;
	private Path pathToTask;
	private final GameWorld world;

	public CollectOreTask(int gameObjectID, Point2D oreLocation, GameWorld world) {
		super(TaskType.COLLECT_ORE, gameObjectID);
		this.location = oreLocation;
		this.world = world;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster) {
		MoveAction moveToOreAction = MoveAction.plan(request);
		Action[] plannedActions = new Action[]{moveToOreAction};
		Assignment deliveryAssignment = taskMaster.assignTask(new MapTaskRequest())
		return new Assignment(new Plan(plannedActions));
	}
}
