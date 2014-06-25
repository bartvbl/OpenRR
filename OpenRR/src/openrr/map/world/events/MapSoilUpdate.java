package openrr.map.world.events;

import openrr.map.soil.SoilType;
import orre.geom.Point2D;

public class MapSoilUpdate {
	public final Point2D location;
	public final SoilType soilType;
	
	public MapSoilUpdate(Point2D location, SoilType type) {
		this.location = location;
		this.soilType = type;
	}
}
