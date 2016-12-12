package openrr.world.core;

import openrr.map.world.events.BatchTileUpdate;
import openrr.map.world.events.MapSoilUpdate;
import orre.gameWorld.core.EnforcedClassEnum;

public enum ORRMessageType implements EnforcedClassEnum {
	TILE_UPDATE(MapSoilUpdate.class), 
	BUILDING_TELEPORT_COMPLETE(Object.class), 
	BATCH_TILE_UPDATE(BatchTileUpdate.class),
	;
	
	private final Class<?> classValue;
	
	private ORRMessageType(Class<?> classValue) {
		this.classValue = classValue;
	}

	@Override
	public Class<?> getClassValue() {
		return classValue;
	}

}
