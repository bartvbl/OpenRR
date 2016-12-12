package openrr.map.world.events;

import openrr.map.MapTile;
import orre.geom.Point2D;
import orre.util.Queue;

public class BatchTileUpdate {

	private final Queue<MapTile> updatedTiles;
	private final Queue<Point2D> updateLocations;
	
	private MapTile currentTile = null;
	private Point2D currentLocation = null;

	public BatchTileUpdate() {
		this.updatedTiles = new Queue<MapTile>();
		this.updateLocations = new Queue<Point2D>();
	}
	
	public boolean hasRemaining() {
		return !updatedTiles.isEmpty();
	}

	public void next() {
		currentTile = updatedTiles.dequeue();
		currentLocation = updateLocations.dequeue();
	}
	
	public MapTile getUpdateTile() {
		return currentTile;
	}
	
	public Point2D getUpdateLocation() {
		return currentLocation;
	}

	public void enqueueTileUpdate(MapTile newTile, Point2D location) {
		this.updatedTiles.enqueue(newTile);
		this.updateLocations.enqueue(location);
	}

}
