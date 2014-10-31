package openrr.world.properties.utility;

import static openrr.ai.TaskType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import openrr.ai.TaskType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;

public class PrioritiesUpdater extends Property {
	private TaskType[] taskTypes = new TaskType[]{
			DRILL,
			COLLECT_CHRYSTAL,
			COLLECT_ORE,
			CLEAR_RUBBLE,
			DRIVE_VEHICLE,
			GET_TOOL,
			IDLE
	};

	public PrioritiesUpdater(GameObject gameObject) {
		super(ORRPropertyType.PRIORITIES_UPDATER, gameObject);
		
		gameObject.world.services.aiService.updatePriorities(taskTypes);
		gameObject.setPropertyData(ORRPropertyDataType.PRIORITIES, Arrays.asList(taskTypes));
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
