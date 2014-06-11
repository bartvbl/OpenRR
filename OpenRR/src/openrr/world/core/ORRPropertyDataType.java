package openrr.world.core;

import openrr.map.world.MapTileReader;

public enum ORRPropertyDataType {
	MAP_TILES(MapTileReader.class), 
	;
	
	public final Class<?> expectedReturnDataType;

	private ORRPropertyDataType(Class<?> dataType) {
		this.expectedReturnDataType = dataType;
	}
}
