package openrr.ai.actions;

import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;

public class PickupAction extends Action {
	
	public static PickupAction plan(TaskRequest request, GameWorld world) {
		return new PickupAction();
	}
	
	private PickupAction() {
		
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
		return true;
	}

	@Override
	public double getCost() {
		return 0; //0: not relevant for cost calculations
	}

}
