package openrr.world.properties.transportables;

import openrr.ai.tasks.DeliverOreTask;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point3D;
import orre.geom.mesh.Model;

public class OreAcceptingBuilding extends Property {
	public OreAcceptingBuilding(GameObject gameObject) {
		super(ORRPropertyType.ORE_ACCEPTING_BUILDING, gameObject);
		this.gameObject.tickOnce(this);
	}

	@Override
	public void handleMessage(Message<?> message) {
	}

	@Override
	public void tick() {
		Model appearance = (Model) gameObject.requestPropertyData(PropertyDataType.APPEARANCE, Model.class);
		Point3D location = appearance.getRootNode().getLocation();
		this.gameObject.world.services.aiService.registerTask(new DeliverOreTask(gameObject.id, location.in2D()));
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
	}

}
