package openrr.ai.actions;

import openrr.animation.AnimationType;
import orre.ai.tasks.Action;
import orre.animation.Animation;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageHandler;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Mesh3D;

public class AnimationAction extends Action implements MessageHandler {
	private boolean hasStarted = false;
	private boolean isFinished = false;
	
	private final Mesh3D rootNode;
	private final GameWorld world;
	private final AnimationType animationType;
	private final Animation animation;

	private int animationID;

	
	public static AnimationAction plan(int targetID, AnimationType type, GameWorld world) {
		Mesh3D rootNode = (Mesh3D) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Mesh3D.class);
		return new AnimationAction(rootNode, type, world);
	}
	
	public static AnimationAction plan(int targetID, Animation animation, GameWorld world) {
		Mesh3D rootNode = (Mesh3D) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Mesh3D.class);
		return new AnimationAction(rootNode, animation, world);
	}
	
	private AnimationAction(Mesh3D rootNode, AnimationType type, GameWorld world) {
		this.rootNode = rootNode;
		this.animationType = type;
		this.world = world;
		this.animation = null;
		world.addMessageListener(MessageType.ANIMATION_ENDED, this);
	}

	public AnimationAction(Mesh3D rootNode, Animation animation, GameWorld world) {
		this.rootNode = rootNode;
		this.animation = animation;
		this.world = world;
		this.animationType = null;
		world.addMessageListener(MessageType.ANIMATION_ENDED, this);
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
			int finishedAnimationID = (Integer) message.getPayload();
			if(this.animationID == finishedAnimationID) {
				this.isFinished = true;
			}
		}
	}

	@Override
	public void start() {
		if(animation == null) {
			this.animationID = world.services.animationService.applyAnimation(animationType.toString(), rootNode);
		} else {
			this.animationID = world.services.animationService.applyAnimation(animation, rootNode);
		}
		this.hasStarted = true;
	}
	
	public int getAnimationPlayheadID() {
		if(!hasStarted) {
			throw new RuntimeException("Animation action has not started yet.");
		}
		return animationID;
	}

	@Override
	public void end() {
		world.removeMessageListener(MessageType.ANIMATION_ENDED, this);
	}

}