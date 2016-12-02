package openrr.world.core;

import openrr.ai.tasks.core.RockRaiderTaskExecutor;
import openrr.ai.tasks.core.ToolStoreTaskExecutor;
import openrr.map.world.MapAppearance;
import openrr.map.world.MapModel;
import openrr.world.buildings.docks.DocksAppearance;
import openrr.world.buildings.miningLaser.MiningLaserAppearance;
import openrr.world.buildings.oreRefinery.OreRefineryAppearance;
import openrr.world.buildings.placers.DockPlacer;
import openrr.world.buildings.placers.MiningLaserPlacer;
import openrr.world.buildings.placers.OreRefineryPlacer;
import openrr.world.buildings.placers.PowerStationPlacer;
import openrr.world.buildings.placers.SmallTeleportPlacer;
import openrr.world.buildings.placers.SnackbarPlacer;
import openrr.world.buildings.placers.SuperTeleportPlacer;
import openrr.world.buildings.placers.SupportStationPlacer;
import openrr.world.buildings.placers.ToolStorePlacer;
import openrr.world.buildings.placers.UpgradeStationPlacer;
import openrr.world.buildings.powerStation.PowerStationAppearance;
import openrr.world.buildings.smallTeleport.TeleportPadAppearance;
import openrr.world.buildings.snackbar.SnackbarAppearance;
import openrr.world.buildings.superTeleport.SuperTeleportAppearance;
import openrr.world.buildings.supportStation.SupportStationAppearance;
import openrr.world.buildings.toolStore.ToolStoreAppearance;
import openrr.world.buildings.upgradeStation.UpgradeStationAppearance;
import openrr.world.creatures.monster.MonsterTaskExecutor;
import openrr.world.creatures.rockRaider.RockRaiderAppearance;
import openrr.world.creatures.rockRaider.RockRaiderMovementSpeed;
import openrr.world.creatures.slug.SlugTaskExecutor;
import openrr.world.properties.Flashlight;
import openrr.world.properties.GravityProperty;
import openrr.world.properties.HealthProperty;
import openrr.world.properties.input.KeyboardMapController;
import openrr.world.properties.input.MouseProbeTracker;
import openrr.world.properties.input.MouseTileSelector;
import openrr.world.properties.transportables.ChrystalAppearance;
import openrr.world.properties.transportables.OreAcceptingBuilding;
import openrr.world.properties.transportables.OreAppearance;
import openrr.world.properties.transportables.Transportable;
import openrr.world.properties.utility.PrioritiesUpdater;
import orre.gameWorld.core.Property;

public enum ORRPropertyType {
	//Appearances
	ORE_APPEARANCE(OreAppearance.class), 
	CHRYSTAL_APPEARANCE(ChrystalAppearance.class),
	MAP_APPEARANCE(MapAppearance.class), 
	TOOL_STORE_APPEARANCE(ToolStoreAppearance.class),
	DOCK_APPEARANCE(DocksAppearance.class), 
	TELEPORT_PAD_APPEARANCE(TeleportPadAppearance.class), 
	POWER_STATION_APPEARANCE(PowerStationAppearance.class),
	ORE_REFINERY_APPEARANCE(OreRefineryAppearance.class),
	SUPPORT_STATION_APPEARANCE(SupportStationAppearance.class), 
	SUPER_TELEPORT_APPEARANCE(SuperTeleportAppearance.class),
	UPGRADE_STATION_APPEARANCE(UpgradeStationAppearance.class),
	ROCK_RAIDER_APPEARANCE(RockRaiderAppearance.class), 
	MINING_LASER_APPEARANCE(MiningLaserAppearance.class), 
	SNACKBAR_APPEARANCE(SnackbarAppearance.class), 
	
	//Placers
	TOOL_STORE_PLACER(ToolStorePlacer.class),
	DOCK_PLACER(DockPlacer.class), 
	SMALL_TELEPORT_PLACER(SmallTeleportPlacer.class),
	ORE_REFINERY_PLACER(OreRefineryPlacer.class), 
	POWER_STATION_PLACER(PowerStationPlacer.class), 
	SUPPORT_STATION_PLACER(SupportStationPlacer.class),
	SUPER_TELEPORT_PLACER(SuperTeleportPlacer.class),
	UPGRADE_STATION_PLACER(UpgradeStationPlacer.class), 
	MINING_LASER_PLACER(MiningLaserPlacer.class),
	SNACKBAR_PLACER(SnackbarPlacer.class),
	
	HEALTH(HealthProperty.class), 
	LIGHT(Flashlight.class), 
	GRAVITY(GravityProperty.class), 
	TRANSPORTABLE(Transportable.class), 
	MAP_MODEL(MapModel.class),
	KEYBOARD_MAP_CONTROLLER(KeyboardMapController.class), 
	MOUSE_PROBE_TRACKER(MouseProbeTracker.class), 
	SLUG_TASK_EXECUTOR(SlugTaskExecutor.class),
	PRIORITIES_UPDATER(PrioritiesUpdater.class), 
	ORE_ACCEPTING_BUILDING(OreAcceptingBuilding.class), 
	ROCK_RAIDER_MOVEMENT(RockRaiderMovementSpeed.class),
	MOUSE_TILE_SELECTOR(MouseTileSelector.class),
	
// Task Executors
	ROCK_RAIDER_TASK_EXECUTOR(RockRaiderTaskExecutor.class),
	TOOL_STORE_TASK_EXECUTOR(ToolStoreTaskExecutor.class),
	MONSTER_TASK_EXECUTOR(MonsterTaskExecutor.class), 
	;
	
	public final Class<? extends Property> propertyClass;

	private ORRPropertyType(Class<? extends Property> propertyClass) {
		this.propertyClass = propertyClass;
	}
}
