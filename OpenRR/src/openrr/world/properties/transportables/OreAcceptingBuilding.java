package openrr.world.properties.transportables;

import openrr.ai.tasks.DeliverOreTask;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;

public class OreAcceptingBuilding extends Property {

	public OreAcceptingBuilding(GameObject gameObject) {
		super(ORRPropertyType.ORE_ACCEPTING_BUILDING, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		this.gameObject.world.services.aiService.registerTask(new DeliverOreTask(gameObject.id, gameObject.world));
	}

}
