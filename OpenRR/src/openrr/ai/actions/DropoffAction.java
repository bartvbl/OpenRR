package openrr.ai.actions;

import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;

public class DropoffAction  extends Action {
	
	public static DropoffAction plan(TaskRequest request, GameWorld world) {
		return new DropoffAction();
	}
	
	private DropoffAction() {
		
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
