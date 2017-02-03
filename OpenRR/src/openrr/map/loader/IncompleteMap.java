package openrr.map.loader;

import openrr.map.Map;
import openrr.map.MapTile;
import openrr.world.core.ORRResourceType;
import orre.resources.Resource;
import orre.resources.ResourceCache;
import orre.resources.ResourceObject;
import orre.resources.ResourceType;
import orre.sceneGraph.SceneNode;

public class IncompleteMap implements ResourceObject<Map> {

	private MapTile[][] tileMap;
	private MapTexturePack texturePack;
	private Map map;

	public IncompleteMap(MapTile[][] tileMap, MapTexturePack texturePack) {
		this.tileMap = tileMap;
		this.texturePack = texturePack;
	}

	public Resource finalizeResource() {
		texturePack.finalizeTextures();
		Map map = new Map(tileMap, texturePack);
		map.buildAll();
		this.map = map;
		return new Resource(ORRResourceType.map, "MAP", Map.class, map);
	}

	public SceneNode createSceneNode() {
		return map.createSceneNode();
	}

	@Override
	public Map createInstance() {
		return null;
	}
}
