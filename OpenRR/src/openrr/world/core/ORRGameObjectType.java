package openrr.world.core;

import static openrr.world.core.ORRPropertyType.*;
import static orre.gameWorld.core.PropertyType.*;

public enum ORRGameObjectType {
	//interface
	FLASHLIGHT				(new Enum<?>[]{ORRPropertyType.LIGHT}),
	BUILDING_PLACEMENT		(new Enum<?>[]{}),
	MAP_CONTROLLER			(new Enum<?>[]{KEYBOARD_MAP_CONTROLLER}),
	MOUSE_TRACKER			(new Enum<?>[]{MOUSE_PROBE_TRACKER}),
	
	//utility
	PRIORITIES_UPDATER		(new Enum<?>[]{ORRPropertyType.PRIORITIES_UPDATER}),
	
	//building placers
	TOOL_STORE_PLACER		(new Enum<?>[]{ORRPropertyType.TOOL_STORE_PLACER}),
	SMALL_TELEPORT_PLACER	(new Enum<?>[]{ORRPropertyType.SMALL_TELEPORT_PLACER}),
	DOCK_PLACER				(new Enum<?>[]{ORRPropertyType.DOCK_PLACER}),
	ORE_REFINERY_PLACER		(new Enum<?>[]{ORRPropertyType.ORE_REFINERY_PLACER}),
	POWER_STATION_PLACER	(new Enum<?>[]{ORRPropertyType.POWER_STATION_PLACER}),
	SUPPORT_STATION_PLACER	(new Enum<?>[]{ORRPropertyType.SUPPORT_STATION_PLACER}),
	SNACKBAR_PLACER			(new Enum<?>[]{ORRPropertyType.SNACKBAR_PLACER}),
	SUPER_TELEPORT_PLACER	(new Enum<?>[]{ORRPropertyType.SUPER_TELEPORT_PLACER}),
	UPGRADE_STATION_PLACER	(new Enum<?>[]{ORRPropertyType.UPGRADE_STATION_PLACER}),
	MINING_LASER_PLACER		(new Enum<?>[]{ORRPropertyType.MINING_LASER_PLACER}),
	
	//map
	MAP						(new Enum<?>[]{MAP_MODEL, MAP_APPEARANCE}),
	
	//characters
	ROCK_RAIDER				(new Enum<?>[]{HEALTH, ROCK_RAIDER_APPEARANCE, GRAVITY, ROCK_RAIDER_TASK_EXECUTOR}), 
	MONSTER					(new Enum<?>[]{HEALTH, GRAVITY, MONSTER_TASK_EXECUTOR}), 
	SLIMY_SLUG				(new Enum<?>[]{HEALTH, GRAVITY, SLUG_TASK_EXECUTOR}),
	BATS					(new Enum<?>[]{}),
	SPIDER					(new Enum<?>[]{GRAVITY}),
	
	//vehicles
	HOVER_SCOUT				(new Enum<?>[]{}),
	SMALL_DIGGER			(new Enum<?>[]{}),
	SMALL_TRANSPORT			(new Enum<?>[]{}),
	SMALL_LASER_CUTTER		(new Enum<?>[]{}),
	RAPID_RIDER				(new Enum<?>[]{}),
	TUNNEL_SCOUT			(new Enum<?>[]{}),
	LOADER_DOZER			(new Enum<?>[]{}),
	GRANITE_GRINDER			(new Enum<?>[]{}),
	CARGO_CARRIER			(new Enum<?>[]{}),
	LARGE_LASER_CUTTER		(new Enum<?>[]{}),
	CHROME_CRUSHER			(new Enum<?>[]{}),
	TUNNEL_TRANSPORT		(new Enum<?>[]{}),
	LMS_EXPLORER			(new Enum<?>[]{}),
	
	//transportables
	CHRYSTAL				(new Enum<?>[]{CHRYSTAL_APPEARANCE, TRANSPORTABLE, GRAVITY}),
	ORE						(new Enum<?>[]{TRANSPORTABLE, ORE_APPEARANCE, GRAVITY}),
	DYNAMITE				(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),
	BUILDING_STUD			(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),
	CONSTRUCTION_BARRIER	(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),
	ELECTRIC_FENCE			(new Enum<?>[]{TRANSPORTABLE, GRAVITY}),		
	
	//buildings
	TOOL_STORE				(new Enum<?>[]{TOOL_STORE_APPEARANCE, GRAVITY}),//TASK_EXECUTOR
	TELEPORT_PAD			(new Enum<?>[]{TELEPORT_PAD_APPEARANCE, GRAVITY}),//TASK_EXECUTOR
	DOCKS					(new Enum<?>[]{DOCK_APPEARANCE, GRAVITY}),//TASK_EXECUTOR
	POWER_STATION			(new Enum<?>[]{POWER_STATION_APPEARANCE, GRAVITY}),
	SUPPORT_STATION			(new Enum<?>[]{SUPPORT_STATION_APPEARANCE, GRAVITY}),
	ORE_REFINERY			(new Enum<?>[]{ORE_REFINERY_APPEARANCE, GRAVITY}),
	UPGRADE_STATION			(new Enum<?>[]{UPGRADE_STATION_APPEARANCE, GRAVITY}),
	GEOLOGICAL_CENTER		(new Enum<?>[]{}),
	MINING_LASER			(new Enum<?>[]{MINING_LASER_APPEARANCE, GRAVITY}),
	SUPER_TELEPORT			(new Enum<?>[]{SUPER_TELEPORT_APPEARANCE, GRAVITY}),//TASK_EXECUTOR
	SNACKBAR				(new Enum<?>[]{SNACKBAR_APPEARANCE, GRAVITY}),
	
	//effects
	LANDSLIDE				(new Enum<?>[]{}),
	BLASTER_BULLET			(new Enum<?>[]{}),
	
	//objectives
	BUILDING_SITE			(new Enum<?>[]{}), 
	;
	
	public final Enum<?>[] properties;

	private ORRGameObjectType(Enum<?>[] properties) {
		this.properties = properties;
	}
}
