package openrr.world.core;

import static openrr.world.core.ORRPropertyType.*;
import static orre.gameWorld.core.PropertyType.*;

public enum ORRGameObjectType {
	ROCK_RAIDER(new String[]{HEALTH.toString(), GRAVITY.toString(), ROCK_RAIDER_APPEARANCE.toString(), TASK_EXECUTOR.toString()}), 
	MONSTER(new String[]{}), 
	CHRYSTAL(new String[]{TRANSPORTABLE.toString(), GRAVITY.toString(), CHRYSTAL_APPEARANCE.toString()}),
	ORE(new String[]{TRANSPORTABLE.toString(), GRAVITY.toString(), ORE_APPEARANCE.toString()}),
	LIGHT(new String[]{ORRPropertyType.LIGHT.toString()});
	
	public final String[] properties;

	private ORRGameObjectType(String[] properties) {
		this.properties = properties;
	}
}
