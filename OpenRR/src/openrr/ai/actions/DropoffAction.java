package openrr.ai.actions;

import openrr.animation.AnimationType;
import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageHandler;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Model;

public class DropoffAction extends Action implements MessageHandler {
	
private boolean isFinished = false;
	
	private final Model rootNode;
	private final GameWorld world;
	
	public static DropoffAction plan(TaskRequest request, GameWorld world) {
		Model rootNode = (Model) world.requestPropertyData(request.targetID, PropertyDataType.APPEARANCE, null, Model.class);
		return new DropoffAction(rootNode, world);
	}
	
	private DropoffAction(Model rootNode, GameWorld world) {
		this.rootNode = rootNode;
		this.world = world;
	}

	@Override
	public boolean isExecutionPossible() {
		return true; //Always possible
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public double getCost() {
		return 0; //0: not relevant for cost calculations
	}

	@Override
	public void handleMessage(Message<?> message) {
		if(message.type == MessageType.ANIMATION_ENDED) {
			this.isFinished = true;
		}
	}

	@Override
	public void start() {
		world.services.animationService.applyAnimation(AnimationType.raiderPutdown.toString(), rootNode);
		world.addMessageListener(MessageType.ANIMATION_ENDED, this);
	}

	@Override
	public void end() {
		
	}
}
