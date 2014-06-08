package openrr.world.core;

import openrr.world.properties.ChrystalAppearance;
import openrr.world.properties.Flashlight;
import openrr.world.properties.GravityProperty;
import openrr.world.properties.HealthProperty;
import openrr.world.properties.OreAppearance;
import openrr.world.properties.RockRaiderAppearance;
import openrr.world.properties.Transportable;
import orre.gameWorld.core.Property;

public enum ORRPropertyType {
	HEALTH(HealthProperty.class), 
	LIGHT(Flashlight.class), 
	GRAVITY(GravityProperty.class), 
	ROCK_RAIDER_APPEARANCE(RockRaiderAppearance.class), 
	TRANSPORTABLE(Transportable.class), 
	ORE_APPEARANCE(OreAppearance.class), 
	CHRYSTAL_APPEARANCE(ChrystalAppearance.class);
	
	public final Class<?> propertyClass;

	private ORRPropertyType(Class<? extends Property> propertyClass) {
		this.propertyClass = propertyClass;
	}
}
