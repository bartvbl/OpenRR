package openrr.ai.actions;

import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageHandler;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Mesh3D;
import orre.geom.mesh.Model;

public class PickupAction extends Action implements MessageHandler {
	private boolean isFinished = false;
	
	private final Model rootNode;
	private final GameWorld world;
	private final int pickupObjectID;
	
	public static PickupAction plan(TaskRequest request, int pickupObjectID, GameWorld world) {
		Model rootNode = (Model) world.requestPropertyData(request.targetID, PropertyDataType.APPEARANCE, null, Model.class);
		return new PickupAction(rootNode, pickupObjectID, world);
	}
	
	private PickupAction(Model rootNode, int pickupObjectID, GameWorld world) {
		this.rootNode = rootNode;
		this.world = world;
		this.pickupObjectID = pickupObjectID;
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
		world.services.animationService.applyAnimation(AnimationType.raiderPickup, rootNode);
		world.addMessageListener(MessageType.ANIMATION_ENDED, this);
		world.despawnObject(pickupObjectID);
	}

	@Override
	public void end() {
		
	}

}
