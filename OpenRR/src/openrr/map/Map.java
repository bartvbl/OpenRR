package openrr.map;

import openrr.map.loader.MapTexturePack;
import openrr.map.world.MapTileReader;
import openrr.map.world.events.MapSoilUpdate;
import orre.geom.Point2D;
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

	public void setSoil(MapSoilUpdate update) {
		if((update.location.x < 0) || (update.location.y < 0) || (update.location.x >= tileMap.length) || (update.location.y >= tileMap[0].length)) {
			return;
		}
		MapTile currentTile = tileMap[update.location.x][update.location.y];
		MapTile updatedTile = new MapTile(currentTile.isWall(), update.soilType, currentTile.tileHeight);
		tileMap[update.location.x][update.location.y] = updatedTile;
	}

	public void setTileAt(MapTile newTile, Point2D location) {
		if((location.x < 0) || (location.y < 0) || (location.x >= tileMap.length) || (location.y >= tileMap[0].length)) {
			return;
		}
		tileMap[location.x][location.y] = newTile;
	}



	
}
