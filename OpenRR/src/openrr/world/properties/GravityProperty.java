package openrr.world.properties;

import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.gameWorld.core.PropertyType;
import orre.geom.Point3D;
import orre.geom.mesh.Mesh3D;

public class GravityProperty extends Property {

	private Mesh3D appearance;
	private MapTileReader reader;

	public GravityProperty(GameObject gameObject) {
		super(ORRPropertyType.GRAVITY, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		Point3D location = appearance.root.getLocation();
		double tileHeight = reader.getTileHeightAt(location.x, location.y);
		appearance.root.setZ(tileHeight);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		this.appearance = (Mesh3D) gameObject.requestPropertyData(PropertyDataType.APPEARANCE, Mesh3D.class);
		int mapID = gameObject.world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
	}

}
