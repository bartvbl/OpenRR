package openrr.world.properties.transportables;

import openrr.ai.TaskType;
import openrr.ai.tasks.CollectOreTask;
import openrr.world.core.ORRPropertyType;
import orre.ai.tasks.Task;
import orre.animation.Animatable;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.gameWorld.core.PropertyType;
import orre.geom.Point3D;
import orre.geom.mesh.Mesh3D;
import orre.geom.mesh.Model;
import orre.gl.texture.Texture;
import orre.sceneGraph.SceneNode;

public class Transportable extends Property {
	private boolean isRegisteredForPickup = false;

	public Transportable(GameObject gameObject) {
		super(ORRPropertyType.TRANSPORTABLE, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		if(!isRegisteredForPickup) {
			Model appearance = (Model) gameObject.requestPropertyData(PropertyDataType.APPEARANCE, Model.class);
			Point3D location = appearance.getRootNode().getLocation();
			gameObject.world.services.aiService.registerTask(new CollectOreTask(gameObject.id, location.in2D(), gameObject.world));
			isRegisteredForPickup = true;
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}
}
