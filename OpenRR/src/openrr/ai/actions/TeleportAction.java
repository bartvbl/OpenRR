package openrr.ai.actions;

import java.util.Arrays;

import openrr.world.buildings.animations.BuildingAnimationGenerator;
import openrr.world.core.ORRGameObjectType;
import openrr.world.util.WorldUtil;
import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.animation.Animation;
import orre.animation.AnimationType;
import orre.gameWorld.chaining.ChainUtil;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageType;
import orre.geom.mesh.Model;
import orre.sceneGraph.CoordinateNode;

public class TeleportAction extends Action {
	
	private final TaskRequest request;
	private final GameWorld world;
	private AnimationAction action;
	private int raiderID;

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
		this.raiderID = world.spawnGameObject(ORRGameObjectType.ROCK_RAIDER);
		
		Model rockRaiderAppearance = WorldUtil.getAppearance(raiderID, world);
		Animation teleportRaiderAnimation = BuildingAnimationGenerator.generateAnimationFor(rockRaiderAppearance);
		this.action = AnimationAction.plan(raiderID, teleportRaiderAnimation, world);
		CoordinateNode teleporterRoot = WorldUtil.getRootNode(request.targetID, world);
		CoordinateNode raiderRoot = WorldUtil.getRootNode(raiderID, world);
		raiderRoot.setLocation(teleporterRoot.getX(), teleporterRoot.getY(), 0);
		raiderRoot.setRotationZ(teleporterRoot.getRotationZ() + 90);
		action.start();
	}

	@Override
	public void end() {
		world.dispatchMessage(new Message<Object>(MessageType.START_EXECUTING_TASKS), raiderID);
	}

}
