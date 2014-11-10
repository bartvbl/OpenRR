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
import orre.sceneGraph.CoordinateNode;

public class PickupAction extends Action implements MessageHandler {
	private boolean hasStarted = false;
	private boolean isFinished = false;
	
	private final Mesh3D rootNode;
	private final GameWorld world;
	private final int pickupObjectID;
	
	public static PickupAction plan(TaskRequest request, int pickupObjectID, GameWorld world) {
		Mesh3D rootNode = (Mesh3D) world.requestPropertyData(request.targetID, PropertyDataType.APPEARANCE, null, Mesh3D.class);
		return new PickupAction(rootNode, pickupObjectID, world);
	}
	
	private PickupAction(Mesh3D rootNode, int pickupObjectID, GameWorld world) {
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
		if(!hasStarted) {
			world.services.animationService.applyAnimation(AnimationType.raiderPickup, rootNode);
			world.addMessageListener(MessageType.ANIMATION_ENDED, this);
			hasStarted = true;
			world.despawnObject(pickupObjectID);
		}
		
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

}
