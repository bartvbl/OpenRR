package openrr.world.creatures.rockRaider;

import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import openrr.ai.actions.movement.MovementSettingsProperty;
import openrr.world.core.ORRPropertyType;

public class RockRaiderMovementSpeed extends MovementSettingsProperty {

	public RockRaiderMovementSpeed(GameObject gameObject) {
		super(ORRPropertyType.ROCK_RAIDER_MOVEMENT, gameObject);
	}

	@Override
	protected double getSoilMovementSpeed() {
		return 0.02;
	}

	@Override
	protected double getRubbleMovementSpeed() {
		return 0.05;
	}

	@Override
	protected double getWaterMovementSpeed() {
		return 0.01;
	}

	@Override
	protected double getLavaMovementSpeed() {
		return 0.01;
	}

	@Override
	protected double getPowerPathMovementSpeed() {
		return 0.18;
	}

	@Override
	protected double getAirMovementSpeed() {
		return 0;
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void destroy() {
		
	}


}
