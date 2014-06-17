package openrr.world.core;

import static openrr.world.core.ORRPropertyType.*;
import static orre.gameWorld.core.PropertyType.*;

public enum ORRGameObjectType {
	//interface
	FLASHLIGHT			(new Enum<?>[]{ORRPropertyType.LIGHT}),
	BUILDING_PLACEMENT	(new Enum<?>[]{}),
	MAP_CONTROLLER		(new Enum<?>[]{KEYBOARD_MAP_CONTROLLER}),
	MOUSE_TRACKER		(new Enum<?>[]{MOUSE_PROBE_TRACKER}),
	
	//map
	MAP					(new Enum<?>[]{MAP_MODEL, MAP_APPEARANCE}),
	
	//characters
	ROCK_RAIDER			(new Enum<?>[]{HEALTH, ROCK_RAIDER_APPEARANCE, GRAVITY, TASK_EXECUTOR}), 
	MONSTER				(new Enum<?>[]{HEALTH, GRAVITY, TASK_EXECUTOR}), 
	SLIMY_SLUG			(new Enum<?>[]{HEALTH, GRAVITY, TASK_EXECUTOR}),
	BATS				(new Enum<?>[]{}),
	SPIDER				(new Enum<?>[]{GRAVITY}),
	
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
	LMS_EXPLORER		(new Enum<?>[]{}),
	
	//transportables
	CHRYSTAL			(new Enum<?>[]{CHRYSTAL_APPEARANCE, TRANSPORTABLE, GRAVITY}),
	ORE					(new Enum<?>[]{TRANSPORTABLE, GRAVITY, ORE_APPEARANCE}),
	DYNAMITE			(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),
	BUILDING_STUD		(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),
	CONSTRUCTION_BARRIER(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),
	ELECTRIC_FENCE		(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),		
	
	//buildings
	TOOL_STORE			(new Enum<?>[]{TOOL_STORE_APPEARANCE, GRAVITY}),//TASK_EXECUTOR
	TELEPORT_PAD		(new Enum<?>[]{TASK_EXECUTOR}),
	DOCKS				(new Enum<?>[]{TASK_EXECUTOR}),
	POWER_STATION		(new Enum<?>[]{}),
	SUPPORT_STATION		(new Enum<?>[]{}),
	ORE_REFINERY		(new Enum<?>[]{}),
	UPGRADE_STATION		(new Enum<?>[]{}),
	GEOLOGICAL_CENTER	(new Enum<?>[]{}),
	MINING_LASER		(new Enum<?>[]{}),
	SUPER_TELEPORT		(new Enum<?>[]{TASK_EXECUTOR}),
	
	//effects
	LANDSLIDE			(new Enum<?>[]{}),
	BLASTER_BULLET		(new Enum<?>[]{}),
	
	//objectives
	BUILDING_SITE		(new Enum<?>[]{}), 
	;
	
	public final Enum<?>[] properties;

	private ORRGameObjectType(Enum<?>[] properties) {
		this.properties = properties;
	}
}
