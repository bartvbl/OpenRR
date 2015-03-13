package openrr.world.properties.utility;

import java.util.Arrays;

import openrr.ai.TaskType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;

public class PrioritiesUpdater extends Property {
	public PrioritiesUpdater(GameObject gameObject) {
		super(ORRPropertyType.PRIORITIES_UPDATER, gameObject);
		
		gameObject.world.services.aiService.updatePriorities(TaskType.values());
		gameObject.setPropertyData(ORRPropertyDataType.PRIORITIES, Arrays.asList(TaskType.values()));
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
		
	}

}
