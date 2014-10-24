package openrr.world.buildings.animations;

import orre.animation.Animation;
import orre.animation.AnimationAction;
import orre.animation.AnimationType;
import orre.animation.Ease;
import orre.animation.KeyFrame;
import orre.animation.actions.MoveAction;
import orre.animation.actions.RotationAction;
import orre.animation.actions.TranslateAction;
import orre.geom.Axis;
import orre.geom.mesh.Model;

public class BuildingAnimationGenerator {
	
	private static final int minHeight = 6;
	private static final int maxHeight = 10;
	private static final int minSize = -3;
	private static final int maxSize = 3;
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
			
			setupActions[3 * i + 0] = new TranslateAction(partName, Axis.z, verticalDistance, Ease.NO_EASE);
			runningActions[3 * i + 0] = new TranslateAction(partName, Axis.z, -verticalDistance, Ease.EASE_IN);
			
			setupActions[3 * i + 1] = new TranslateAction(partName, Axis.x, horizontalDistance, Ease.NO_EASE);
			runningActions[3 * i + 1] = new TranslateAction(partName, Axis.x, -horizontalDistance, Ease.EASE_IN);
			
			setupActions[3 * i + 2] = new RotationAction(partName, Axis.z, rotation, Ease.NO_EASE);
			runningActions[3 * i + 2] = new RotationAction(partName, Axis.z, -rotation, Ease.EASE_IN);
		}
		
		KeyFrame setupFrame = new KeyFrame("setupFrame", 0.0001, false, true, setupActions);
		KeyFrame runningFrame = new KeyFrame("runningFrame", 6, false, false, runningActions);
		
		return new Animation(AnimationType.teleportBuilding, new KeyFrame[]{setupFrame, runningFrame});
	}
}
