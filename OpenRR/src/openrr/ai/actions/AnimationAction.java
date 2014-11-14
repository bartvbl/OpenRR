package openrr.ai.actions;

import orre.ai.tasks.Action;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageHandler;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Mesh3D;

public class AnimationAction extends Action implements MessageHandler {
	private boolean isFinished = false;
	
	private final Mesh3D rootNode;
	private final GameWorld world;
	private final AnimationType animationType;
	
	public static AnimationAction plan(int targetID, AnimationType type, GameWorld world) {
		Mesh3D rootNode = (Mesh3D) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Mesh3D.class);
		return new AnimationAction(rootNode, type, world);
	}
	
	private AnimationAction(Mesh3D rootNode, AnimationType type, GameWorld world) {
		this.rootNode = rootNode;
		this.animationType = type;
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
		world.services.animationService.applyAnimation(animationType, rootNode);
		world.addMessageListener(MessageType.ANIMATION_ENDED, this);
	}

	@Override
	public void end() {
		
	}

}