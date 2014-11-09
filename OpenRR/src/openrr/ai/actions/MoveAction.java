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
import orre.geom.Point2D;
import orre.geom.mesh.Model;
import orre.sceneGraph.CoordinateNode;

public class MoveAction extends Action {

	private static final AStar astar = new AStar();
	private final CoordinateNode targetNode;
	
	public static MoveAction plan(int targetID, Point2D start, Point2D destination, GameWorld world) {
		int mapID = world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		
		MapTileReader reader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		MapTileNode taskLocation = new MapTileNode(reader, start.x, start.y);
		MapTileNode unitLocation = new MapTileNode(reader, destination.x, destination.y);
		Path pathToTask = astar.findPath(unitLocation, taskLocation);
		
		CoordinateNode rootNode = (CoordinateNode) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, CoordinateNode.class);
		
		return new MoveAction(pathToTask, rootNode);
	}
	
	private MoveAction(Path pathToTask, CoordinateNode targetNode) {
		this.path = pathToTask;
		this.targetNode = targetNode;
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
