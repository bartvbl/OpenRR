package openrr.world.core;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.world.MapTileReader;

public enum ORRPropertyDataType {
	MAP_TILES(MapTileReader.class),
	MOUSE_LOCATION(Vector3f.class), 
	PRIORITIES(List.class),
	;
	
	public final Class<?> expectedReturnDataType;

	private ORRPropertyDataType(Class<?> dataType) {
		this.expectedReturnDataType = dataType;
	}
}
