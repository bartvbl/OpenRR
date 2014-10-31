package openrr.ai.actions;

import openrr.ai.MapTileNode;
import openrr.ai.taskRequests.MapTaskRequest;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.pathFinding.Path;
import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Model;

public class MoveAction extends Action {

	private static final AStar astar = new AStar();
	
	public static MoveAction plan(TaskRequest request, GameWorld world) {
		
		if(!(request instanceof MapTaskRequest)) {
			//can't plan a collection task without knowing where the to-be retrieved item is located
			throw new RuntimeException("MoveAction requires a MapTaskRequest");
		}
		
		MapTaskRequest mapRequest = (MapTaskRequest) request;
		int mapID = world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		
		Model appearance = (Model) world.requestPropertyData(request.targetID, PropertyDataType.APPEARANCE, null, Model.class);
		MapTileReader reader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		MapTileNode taskLocation = new MapTileNode(reader, mapRequest.locationOnMap.x, mapRequest.locationOnMap.y);
		MapTileNode unitLocation = new MapTileNode(reader, (int)appearance.getRootNode().getX(), (int)appearance.getRootNode().getY());
		Path pathToTask = astar.findPath(unitLocation, taskLocation);
		
		return new MoveAction(pathToTask);
	}

	
	private MoveAction(Path pathToTask) {
		this.path = pathToTask;
	}
	
	private final Path path;

	@Override
	public boolean isExecutionPossible() {
		return path.isFoundPath;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		return path.hasFinished();
	}

	@Override
	public double getCost() {
		return path.getStepCount();
	}
	
}
