package openrr.map.world;

import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;

public class MapModel extends Property {

	public MapModel(GameObject gameObject) {
		super(ORRPropertyType.MAP_MODEL, gameObject);
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
		this.gameObject.world.resourceCache.getResource(ORRResourceType., name)
	}

}
