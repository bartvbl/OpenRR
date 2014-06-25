package openrr.map.world;

import java.util.ArrayList;

import openrr.map.Map;
import openrr.map.world.events.MapSoilUpdate;
import openrr.world.core.ORRMessageType;
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
import orre.sceneGraph.ContainerNode;
import orre.sceneGraph.CoordinateNode;
import orre.sceneGraph.SceneNode;

public class MapAppearance extends Property {

	private Map map;
	private ContainerNode mapGeometryNode;

	public MapAppearance(GameObject gameObject) {
		super(ORRPropertyType.MAP_APPEARANCE, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		if(message.type == ORRMessageType.TILE_UPDATE) {
			MapSoilUpdate update = (MapSoilUpdate) message.getPayload();
			map.setSoil(update);
			rebuildMap();
		}
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
		this.map = (Map) mapResource.content;
		
		CoordinateNode mapNode = new CoordinateNode();
		this.mapGeometryNode = new ContainerNode("Map geometry container");
		mapNode.addChild(mapGeometryNode);
		rebuildMap();
		this.gameObject.setPropertyData(ORRPropertyDataType.MAP_TILES, map.getMapTileReader());
		this.gameObject.takeControl(new GraphicsObject(mapNode));
		Mesh3D mapMesh = new Mesh3D("map", mapNode);
		this.gameObject.setPropertyData(PropertyDataType.APPEARANCE, mapMesh);
		this.gameObject.world.scene3DRoot.addChild(mapNode);
	}
	
	private void rebuildMap() {
		map.buildAll();
		ArrayList<SceneNode> children = mapGeometryNode.getChildren();
		for(int i = children.size() - 1; i >= 0; i--) {
			mapGeometryNode.removeChild(children.get(i));
		}
		mapGeometryNode.addChild(map.createSceneNode());
	}

}
