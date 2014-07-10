package openrr.ai.actions;

import openrr.ai.MapTileNode;
import openrr.ai.taskRequests.MapTaskRequest;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;

public class MoveAction extends Action {

	private static final AStar astar = new AStar();
	
	public static MoveAction plan(TaskRequest request) {
		
		if(!(request instanceof MapTaskRequest)) {
			//can't plan a collection task without knowing where the to-be retrieved item is located
			return null;
		}
		MapTaskRequest mapRequest = (MapTaskRequest) request;
		int mapID = world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		MapTileReader reader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		MapTileNode unitLocation = new MapTileNode(reader, mapRequest.locationOnMap.x, mapRequest.locationOnMap.y);
		MapTileNode taskLocation = new MapTileNode(reader, location.x, location.y);
		this.pathToTask = astar.findPath(unitLocation, taskLocation);
		
		return new MoveAction();
	}
	
	private MoveAction() {
		
	}
	
	@Override
	public boolean isExecutionPossible() {
		return false;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public double getCost() {
		return 0;
	}
	
}
