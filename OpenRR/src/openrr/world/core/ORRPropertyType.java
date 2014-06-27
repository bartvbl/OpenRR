package openrr.world.core;

import openrr.map.world.MapAppearance;
import openrr.map.world.MapModel;
import openrr.world.buildings.docks.DocksAppearance;
import openrr.world.buildings.placers.DockPlacer;
import openrr.world.buildings.placers.SmallTeleportPlacer;
import openrr.world.buildings.placers.ToolStorePlacer;
import openrr.world.buildings.smallTeleport.TeleportPadAppearance;
import openrr.world.buildings.toolStore.ToolStoreAppearance;
import openrr.world.properties.ChrystalAppearance;
import openrr.world.properties.Flashlight;
import openrr.world.properties.GravityProperty;
import openrr.world.properties.HealthProperty;
import openrr.world.properties.OreAppearance;
import openrr.world.properties.RockRaiderAppearance;
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
	;
	
	public final Class<? extends Property> propertyClass;

	private ORRPropertyType(Class<? extends Property> propertyClass) {
		this.propertyClass = propertyClass;
	}
}
