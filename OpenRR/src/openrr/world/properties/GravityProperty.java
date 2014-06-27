package openrr.world.properties;

import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.animation.Animatable;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.gameWorld.core.PropertyType;
import orre.geom.Point3D;
import orre.geom.mesh.Mesh3D;
import orre.geom.mesh.Model;

public class GravityProperty extends Property {

	private Model appearance;
	private MapTileReader reader;

	public GravityProperty(GameObject gameObject) {
		super(ORRPropertyType.GRAVITY, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		Point3D location = appearance.getRootNode().getLocation();
		double tileHeight = reader.getTileHeightAt(location.x, location.y);
		appearance.getRootNode().setZ(tileHeight);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		this.appearance = (Model) gameObject.requestPropertyData(PropertyDataType.APPEARANCE, Model.class);
		int mapID = gameObject.world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
	}

}
