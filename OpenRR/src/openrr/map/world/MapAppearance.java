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
import orre.gameWorld.properties.Appearance;
import orre.geom.mesh.Mesh3D;
import orre.geom.mesh.ModelPart;
import orre.resources.Resource;
import orre.sceneGraph.CoordinateNode;
import orre.sceneGraph.SceneNode;

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
		Resource mapResource = this.gameObject.world.resourceCache.getResource(ORRResourceType.map, "MAP");
		Map map = (Map) mapResource.content;
		SceneNode mapGeometryNode = map.createSceneNode();
		CoordinateNode mapNode = new CoordinateNode();
		mapNode.addChild(mapGeometryNode);
		this.gameObject.setPropertyData(ORRPropertyDataType.MAP_TILES, map.getMapTileReader());
		this.gameObject.takeControl(new GraphicsObject(mapNode));
		Mesh3D mapMesh = new Mesh3D("map", mapNode);
		this.gameObject.setPropertyData(PropertyDataType.APPEARANCE, mapMesh);
		this.gameObject.world.scene3DRoot.addChild(mapNode);
	}

}
