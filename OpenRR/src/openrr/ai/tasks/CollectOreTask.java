package openrr.ai.tasks;

import openrr.ai.MapTileNode;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.pathFinding.Path;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskType;
import orre.gameWorld.core.GameWorld;
import orre.geom.Point2D;

public class CollectOreTask extends Task {

	private final Point2D location;
	private Path pathToTask;
	private final GameWorld world;
	private static final AStar astar = new AStar();

	public CollectOreTask(int gameObjectID, Point2D oreLocation, GameWorld world) {
		super(TaskType.COLLECT_ORE, gameObjectID);
		this.location = oreLocation;
		this.world = world;
	}

	@Override
	public boolean isExecutionPossible() {
		return pathToTask.isFoundPath;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void plan(Point2D locationOnMap) {
		int mapID = world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		MapTileReader reader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		MapTileNode unitLocation = new MapTileNode(reader, locationOnMap.x, locationOnMap.y);
		MapTileNode taskLocation = new MapTileNode(reader, location.x, location.y);
		this.pathToTask = astar.findPath(unitLocation, taskLocation);
	}

	@Override
	public double getPlanCost() {
		return pathToTask.getStepCount();
	}


}
