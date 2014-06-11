package openrr.world.properties;

import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyType;
import orre.gameWorld.services.InputService;
import orre.gl.lighting.Light;

public class Flashlight extends Property {

	private final InputService service;
	public final Light light;

	public Flashlight(GameObject gameObject) {
		super(ORRPropertyType.LIGHT, gameObject);
		this.light = new Light();
		this.service = this.gameObject.world.services.inputService;
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		float[] mapCoordinates = this.service.getMouseTargetLocation();
		int mapID = gameObject.world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		MapTileReader reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		double height = reader.getTileHeightAt(mapCoordinates[0], mapCoordinates[1]);
		this.light.setPosition(mapCoordinates[0], mapCoordinates[1], height);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}
}

