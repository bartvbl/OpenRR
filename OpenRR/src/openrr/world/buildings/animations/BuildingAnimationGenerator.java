package openrr.world.buildings.animations;

import orre.animation.Animation;
import orre.animation.AnimationAction;
import orre.animation.AnimationType;
import orre.animation.KeyFrame;
import orre.animation.actions.MoveAction;
import orre.animation.actions.RotationAction;
import orre.animation.actions.TranslateAction;
import orre.geom.Axis;
import orre.geom.mesh.Model;

public class BuildingAnimationGenerator {
	
	private static final int minHeight = 6;
	private static final int maxHeight = 10;
	private static final int minSize = -10;
	private static final int maxSize = 10;
	private static final int minRotation = -1400;
	private static final int maxRotation = 1400;

	public static Animation generateAnimationFor(Model model) {
		String[] parts = model.getModelParts();
		
		AnimationAction[] setupActions = new AnimationAction[parts.length * 3];
		AnimationAction[] runningActions = new AnimationAction[parts.length * 3];
		
		for(int i = 0; i < parts.length; i++) {
			String partName = parts[i];
			
			double verticalDistance = minHeight + (Math.random() * (maxHeight - minHeight));
			double horizontalDistance = minSize + (Math.random() * (maxSize - minSize));
			double rotation = minRotation + (Math.random() * (maxRotation - minRotation));
			
			setupActions[3 * i + 0] = new TranslateAction(partName, Axis.z, verticalDistance);
			runningActions[3 * i + 0] = new TranslateAction(partName, Axis.z, -verticalDistance);
			
			setupActions[3 * i + 1] = new TranslateAction(partName, Axis.x, horizontalDistance);
			runningActions[3 * i + 1] = new TranslateAction(partName, Axis.x, -horizontalDistance);
			
			setupActions[3 * i + 2] = new RotationAction(partName, rotation, Axis.z);
			runningActions[3 * i + 2] = new RotationAction(partName, -rotation, Axis.z);
		}
		
		KeyFrame setupFrame = new KeyFrame("setupFrame", 0.0001, false, true, setupActions);
		KeyFrame runningFrame = new KeyFrame("runningFrame", 6, false, false, runningActions);
		
		return new Animation(AnimationType.teleportBuilding, new KeyFrame[]{setupFrame, runningFrame});
	}
}
