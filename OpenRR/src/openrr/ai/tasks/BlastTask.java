package openrr.ai.tasks;

import openrr.ai.TaskType;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.TaskMaster;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.geom.Index2D;

public class BlastTask extends ORRTask {
	
	private final Index2D location;

	public BlastTask(int gameObjectID, Index2D locationOnMap) {
		super(TaskType.BLAST_WALL, gameObjectID);
		this.location = locationOnMap;
	}

	@Override
	public Assignment plan(TaskRequest request, TaskMaster taskMaster, GameWorld world) {
		return null;
	}

}
