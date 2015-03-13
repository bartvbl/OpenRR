package openrr.world.properties.transportables;

import openrr.ai.tasks.CollectOreTask;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point3D;
import orre.geom.mesh.Model;

public class Transportable extends Property {

	public Transportable(GameObject gameObject) {
		super(ORRPropertyType.TRANSPORTABLE, gameObject);
		this.gameObject.tickOnce(this);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		Model appearance = (Model) gameObject.requestPropertyData(PropertyDataType.APPEARANCE, Model.class);
		Point3D location = appearance.getRootNode().getLocation();
		gameObject.world.services.aiService.registerTask(new CollectOreTask(gameObject.id, location.in2D()));
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}
}
