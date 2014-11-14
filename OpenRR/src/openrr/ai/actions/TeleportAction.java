package openrr.ai.actions;

import openrr.world.core.ORRGameObjectType;
import openrr.world.util.WorldUtil;
import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameWorld;
import orre.sceneGraph.CoordinateNode;

public class TeleportAction extends Action {
	
	private final TaskRequest request;
	private final GameWorld world;
	private AnimationAction action;

	private TeleportAction(TaskRequest request, GameWorld world) {
		this.request = request;
		this.world = world;
	}

	public static TeleportAction plan(TaskRequest request, GameWorld world) {
		return new TeleportAction(request, world);
	}

	@Override
	public boolean isExecutionPossible() {
		return true;
	}

	@Override
	public void update() {

	}

	@Override
	public boolean isFinished() {
		return action.isFinished();
	}

	@Override
	public double getCost() {
		return 0;
	}

	@Override
	public void start() {
		System.out.println("Started!");
		int raiderID = world.spawnGameObject(ORRGameObjectType.ROCK_RAIDER);
		this.action = AnimationAction.plan(raiderID, AnimationType.teleportRockRaider, world);
		CoordinateNode teleporterRoot = WorldUtil.getRootNode(request.targetID, world);
		CoordinateNode raiderRoot = WorldUtil.getRootNode(raiderID, world);
		raiderRoot.setLocation(teleporterRoot.getX(), teleporterRoot.getY(), 0);
		raiderRoot.setRotationZ(teleporterRoot.getRotationZ());
	}

	@Override
	public void end() {

	}

}
