package openrr.world.core;

import openrr.map.world.MapAppearance;
import openrr.map.world.MapModel;
import openrr.world.buildings.docks.DocksAppearance;
import openrr.world.buildings.oreRefinery.OreRefineryAppearance;
import openrr.world.buildings.placers.DockPlacer;
import openrr.world.buildings.placers.OreRefineryPlacer;
import openrr.world.buildings.placers.PowerStationPlacer;
import openrr.world.buildings.placers.SmallTeleportPlacer;
import openrr.world.buildings.placers.SnackbarPlacer;
import openrr.world.buildings.placers.SuperTeleportPlacer;
import openrr.world.buildings.placers.SupportStationPlacer;
import openrr.world.buildings.placers.ToolStorePlacer;
import openrr.world.buildings.powerStation.PowerStationAppearance;
import openrr.world.buildings.smallTeleport.TeleportPadAppearance;
import openrr.world.buildings.snackbar.SnackbarAppearance;
import openrr.world.buildings.superTeleport.SuperTeleportAppearance;
import openrr.world.buildings.supportStation.SupportStationAppearance;
import openrr.world.buildings.toolStore.ToolStoreAppearance;
import openrr.world.creatures.monster.MonsterTaskExecutor;
import openrr.world.creatures.rockRaider.RockRaiderAppearance;
import openrr.world.creatures.rockRaider.RockRaiderTaskExecutor;
import openrr.world.creatures.slug.SlugTaskExecutor;
import openrr.world.properties.ChrystalAppearance;
import openrr.world.properties.Flashlight;
import openrr.world.properties.GravityProperty;
import openrr.world.properties.HealthProperty;
import openrr.world.properties.OreAppearance;
import openrr.world.properties.Transportable;
import openrr.world.properties.input.KeyboardMapController;
import openrr.world.properties.input.MouseProbeTracker;
import orre.gameWorld.core.Property;

public enum ORRPropertyType {
	HEALTH(HealthProperty.class), 
	LIGHT(Flashlight.class), 
	GRAVITY(GravityProperty.class), 
	ROCK_RAIDER_APPEARANCE(RockRaiderAppearance.class), 
	TRANSPORTABLE(Transportable.class), 
	ORE_APPEARANCE(OreAppearance.class), 
	CHRYSTAL_APPEARANCE(ChrystalAppearance.class),
	MAP_MODEL(MapModel.class),
	MAP_APPEARANCE(MapAppearance.class), 
	KEYBOARD_MAP_CONTROLLER(KeyboardMapController.class), 
	TOOL_STORE_APPEARANCE(ToolStoreAppearance.class),
	MOUSE_PROBE_TRACKER(MouseProbeTracker.class), 
	TOOL_STORE_PLACER(ToolStorePlacer.class),
	DOCK_APPEARANCE(DocksAppearance.class), 
	DOCK_PLACER(DockPlacer.class), 
	SMALL_TELEPORT_PLACER(SmallTeleportPlacer.class),
	TELEPORT_PAD_APPEARANCE(TeleportPadAppearance.class), 
	ORE_REFINERY_PLACER(OreRefineryPlacer.class), 
	POWER_STATION_PLACER(PowerStationPlacer.class), 
	SUPPORT_STATION_PLACER(SupportStationPlacer.class),
	POWER_STATION_APPEARANCE(PowerStationAppearance.class),
	ORE_REFINERY_APPEARANCE(OreRefineryAppearance.class),
	SUPPORT_STATION_APPEARANCE(SupportStationAppearance.class), 
	SNACKBAR_PLACER(SnackbarPlacer.class),
	SNACKBAR_APPEARANCE(SnackbarAppearance.class), 
	SUPER_TELEPORT_PLACER(SuperTeleportPlacer.class),
	SUPER_TELEPORT_APPEARANCE(SuperTeleportAppearance.class),
	ROCK_RAIDER_TASK_EXECUTOR(RockRaiderTaskExecutor.class),
	MONSTER_TASK_EXECUTOR(MonsterTaskExecutor.class),
	SLUG_TASK_EXECUTOR(SlugTaskExecutor.class),
	;
	
	public final Class<? extends Property> propertyClass;

	private ORRPropertyType(Class<? extends Property> propertyClass) {
		this.propertyClass = propertyClass;
	}
}
