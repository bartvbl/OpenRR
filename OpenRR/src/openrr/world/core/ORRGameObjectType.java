package openrr.world.core;

import static openrr.world.core.ORRPropertyType.*;
import static orre.gameWorld.core.PropertyType.*;

public enum ORRGameObjectType {
	//interface
	FLASHLIGHT			(new Enum<?>[]{ORRPropertyType.LIGHT}),
	
	//map
	MAP					(new Enum<?>[]{}),
	
	//characters
	ROCK_RAIDER			(new Enum<?>[]{HEALTH, GRAVITY, ROCK_RAIDER_APPEARANCE, TASK_EXECUTOR}), 
	MONSTER				(new Enum<?>[]{}), 
	SLIMY_SLUG			(new Enum<?>[]{}),
	BATS				(new Enum<?>[]{}),
	SPIDER				(new Enum<?>[]{}),
	
	//vehicles
	HOVER_SCOUT			(new Enum<?>[]{}),
	SMALL_DIGGER		(new Enum<?>[]{}),
	SMALL_TRANSPORT		(new Enum<?>[]{}),
	SMALL_LASER_CUTTER	(new Enum<?>[]{}),
	RAPID_RIDER			(new Enum<?>[]{}),
	TUNNEL_SCOUT		(new Enum<?>[]{}),
	LOADER_DOZER		(new Enum<?>[]{}),
	GRANITE_GRINDER		(new Enum<?>[]{}),
	CARGO_CARRIER		(new Enum<?>[]{}),
	LARGE_LASER_CUTTER	(new Enum<?>[]{}),
	CHROME_CRUSHER		(new Enum<?>[]{}),
	TUNNEL_TRANSPORT	(new Enum<?>[]{}),
	
	//transportables
	CHRYSTAL			(new Enum<?>[]{TRANSPORTABLE, GRAVITY, CHRYSTAL_APPEARANCE}),
	ORE					(new Enum<?>[]{TRANSPORTABLE, GRAVITY, ORE_APPEARANCE}),
	DYNAMITE			(new Enum<?>[]{}),
	BUILDING_STUD		(new Enum<?>[]{}),
	CONSTRUCTION_BARRIER(new Enum<?>[]{}),
	ELECTRIC_FENCE		(new Enum<?>[]{}),		
	
	//buildings
	TOOL_STORE			(new Enum<?>[]{}),
	TELEPORT_PAD		(new Enum<?>[]{}),
	DOCKS				(new Enum<?>[]{}),
	POWER_STATION		(new Enum<?>[]{}),
	SUPPORT_STATION		(new Enum<?>[]{}),
	ORE_REFINERY		(new Enum<?>[]{}),
	UPGRADE_STATION		(new Enum<?>[]{}),
	GEOLOGICAL_CENTER	(new Enum<?>[]{}),
	MINING_LASER		(new Enum<?>[]{}),
	SUPER_TELEPORT		(new Enum<?>[]{}),
	
	//effects
	LANDSLIDE			(new Enum<?>[]{}),
	BLASTER_BULLET		(new Enum<?>[]{}),
	;
	
	public final Enum<?>[] properties;

	private ORRGameObjectType(Enum<?>[] properties) {
		this.properties = properties;
	}
}
