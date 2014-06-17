package openrr.world.properties;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.world.MapTileReader;
import openrr.map.world.MapWorldUtils;
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

	private Light light;
	private int mouseProbeID;
	private MapTileReader reader;

	public Flashlight(GameObject gameObject) {
		super(ORRPropertyType.LIGHT, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		Vector3f mouseLocation = (Vector3f) gameObject.world.requestPropertyData(mouseProbeID, ORRPropertyDataType.MOUSE_LOCATION, null, Vector3f.class);
		double height = reader.getTileHeightAt(mouseLocation.x, mouseLocation.y);
		this.light.setPosition(mouseLocation.x, mouseLocation.y, height);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		this.light = new Light();
		this.mouseProbeID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MOUSE_TRACKER);
		int mapID = gameObject.world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		gameObject.world.scene3DRoot.addChild(light);
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
	}
}

