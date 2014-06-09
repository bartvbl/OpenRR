package openrr.map.world;

import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.properties.Appearance;

public class MapAppearance extends Property {

	public MapAppearance(GameObject gameObject) {
		super(ORRPropertyType.MAP_APPEARANCE, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {

	}

	@Override
	public void tick() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

}
