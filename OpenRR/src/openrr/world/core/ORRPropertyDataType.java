package openrr.world.core;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import openrr.camera.MapCamera;
import openrr.map.world.MapTileReader;

public enum ORRPropertyDataType {
	MAP_TILES(MapTileReader.class),
	MOUSE_LOCATION(Vector3f.class), 
	PRIORITIES(List.class), 
	MOVEMENT_SPEED_SOIL(Double.class),
	MOVEMENT_SPEED_LAVA(Double.class),
	MOVEMENT_SPEED_WATER(Double.class),
	MOVEMENT_SPEED_POWER_PATH(Double.class),
	MOVEMENT_SPEED_AIR(Double.class),
	MOVEMENT_SPEED_RUBBLE(Double.class), 
	MAP_CAMERA(MapCamera.class),
	;
	
	public final Class<?> expectedReturnDataType;

	private ORRPropertyDataType(Class<?> dataType) {
		this.expectedReturnDataType = dataType;
	}
}
