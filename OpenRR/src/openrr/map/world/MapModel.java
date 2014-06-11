package openrr.map.world;

import openrr.map.Map;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import openrr.world.core.ORRResourceType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.GraphicsObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.resources.Resource;
import orre.sceneGraph.SceneNode;

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
		Resource mapResource = this.gameObject.world.resourceCache.getResource(ORRResourceType.map, "MAP");
		Map map = (Map) mapResource.content;
		SceneNode mapNode = map.createSceneNode();
		this.gameObject.setPropertyData(ORRPropertyDataType.MAP_TILES, map.getMapTileReader());
		this.gameObject.takeControl(new GraphicsObject(mapNode));
		this.gameObject.setPropertyData(PropertyDataType.APPEARANCE, mapNode);
	}

}
