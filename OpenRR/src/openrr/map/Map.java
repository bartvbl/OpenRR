package openrr.map;

import openrr.map.loader.MapTexturePack;
import openrr.map.world.MapTileReader;
import orre.sceneGraph.SceneNode;
import orre.util.MathUtil;

public class Map {
	private final MapGeometryCache cache;
	private final MapTile[][] tileMap;
	private final MapTexturePack texturePack;
	
	public Map(MapTile[][] tileMap, MapTexturePack texturePack) {
		this.tileMap = tileMap;
		this.texturePack = texturePack;
		this.cache = new MapGeometryCache(tileMap, texturePack);
	}

	public SceneNode createSceneNode() {
		return cache.getSceneNode();
	}
	
	public void tick() {
		
	}

	public void buildAll() {
		this.cache.buildAll();
	}

	public MapTileReader getMapTileReader() {
		return new MapTileReader(tileMap);
	}



	
}
