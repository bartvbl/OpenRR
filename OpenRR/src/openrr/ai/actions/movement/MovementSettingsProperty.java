package openrr.ai.actions.movement;

import openrr.world.core.ORRPropertyDataType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Property;

public abstract class MovementSettingsProperty extends Property{

	public MovementSettingsProperty(Enum<?> type, GameObject gameObject) {
		super(type, gameObject);
	}
	
	@Override
	public void init() {
		this.gameObject.setPropertyData(ORRPropertyDataType.MOVEMENT_SPEED_SOIL, getSoilMovementSpeed());
		this.gameObject.setPropertyData(ORRPropertyDataType.MOVEMENT_SPEED_RUBBLE, getRubbleMovementSpeed());
		this.gameObject.setPropertyData(ORRPropertyDataType.MOVEMENT_SPEED_WATER, getWaterMovementSpeed());
		this.gameObject.setPropertyData(ORRPropertyDataType.MOVEMENT_SPEED_LAVA, getLavaMovementSpeed());
		this.gameObject.setPropertyData(ORRPropertyDataType.MOVEMENT_SPEED_POWER_PATH, getPowerPathMovementSpeed());
		this.gameObject.setPropertyData(ORRPropertyDataType.MOVEMENT_SPEED_AIR, getAirMovementSpeed());
	}
	
	protected abstract double getSoilMovementSpeed();
	protected abstract double getRubbleMovementSpeed();
	protected abstract double getWaterMovementSpeed();
	protected abstract double getLavaMovementSpeed();
	protected abstract double getPowerPathMovementSpeed();
	protected abstract double getAirMovementSpeed();
}
