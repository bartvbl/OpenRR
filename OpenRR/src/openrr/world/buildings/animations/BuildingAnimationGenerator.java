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
	private static final int minSize = 0;
	private static final int maxSize = 0;
	private static final int minRotation = -1400;
	private static final int maxRotation = 1400;
	private static final int ACTION_COUNT = 6;

	public static Animation generateAnimationFor(Model model) {
		String[] parts = model.getModelParts();
		
		AnimationAction[] setupActions = new AnimationAction[(parts.length - 1) * ACTION_COUNT];
		AnimationAction[] runningActions = new AnimationAction[(parts.length - 1) * ACTION_COUNT];
		
		int index = 0;
		
		for(int i = 0; i < parts.length; i++) {
			String partName = parts[i];
			if(partName.equals("root")) {
				continue;
			}
			
			double distanceX = minSize + (Math.random() * (maxSize - minSize));
			double distanceY = minSize + (Math.random() * (maxSize - minSize));
			double distanceZ = minHeight + (Math.random() * (maxHeight - minHeight));
			double rotationX = minRotation + (Math.random() * (maxRotation - minRotation));
			double rotationY = minRotation + (Math.random() * (maxRotation - minRotation));
			double rotationZ = minRotation + (Math.random() * (maxRotation - minRotation));
			
			setupActions[ACTION_COUNT * index + 0] = new TranslateAction(partName, Axis.x, distanceX, Ease.NO_EASE);
			runningActions[ACTION_COUNT * index + 0] = new TranslateAction(partName, Axis.x, -distanceX, Ease.EASE_IN);
			
			setupActions[ACTION_COUNT * index + 1] = new TranslateAction(partName, Axis.y, distanceY, Ease.NO_EASE);
			runningActions[ACTION_COUNT * index + 1] = new TranslateAction(partName, Axis.y, -distanceY, Ease.EASE_IN);
			
			setupActions[ACTION_COUNT * index + 2] = new TranslateAction(partName, Axis.z, distanceZ, Ease.NO_EASE);
			runningActions[ACTION_COUNT * index + 2] = new TranslateAction(partName, Axis.z, -distanceZ, Ease.EASE_IN);
			
			
			setupActions[ACTION_COUNT * index + 3] = new RotationAction(partName, Axis.x, rotationX, Ease.NO_EASE);
			runningActions[ACTION_COUNT * index + 3] = new RotationAction(partName, Axis.x, -rotationX, Ease.EASE_IN);
			
			setupActions[ACTION_COUNT * index + 4] = new RotationAction(partName, Axis.y, rotationY, Ease.NO_EASE);
			runningActions[ACTION_COUNT * index + 4] = new RotationAction(partName, Axis.y, -rotationY, Ease.EASE_IN);
			
			setupActions[ACTION_COUNT * index + 5] = new RotationAction(partName, Axis.z, rotationZ, Ease.NO_EASE);
			runningActions[ACTION_COUNT * index + 5] = new RotationAction(partName, Axis.z, -rotationZ, Ease.EASE_IN);
			
			index++;
		}
		
		KeyFrame setupFrame = new KeyFrame("setupFrame", 0.0001, false, true, setupActions);
		KeyFrame runningFrame = new KeyFrame("runningFrame", 6, false, false, runningActions);
		
		return new Animation(AnimationType.teleportBuilding, new KeyFrame[]{setupFrame, runningFrame});
	}
}
