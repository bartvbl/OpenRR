package openrr.ai.tasks;

import openrr.ai.MapTileNode;
import openrr.ai.TaskType;
import openrr.ai.tasks.core.MapTaskRequest;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.pathFinding.Path;
import orre.ai.tasks.Assignment;
import orre.ai.tasks.Plan;
import orre.ai.tasks.Task;
import orre.ai.tasks.TaskRequest;
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
	public Assignment plan(TaskRequest request) {
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
		return null;
	}
}
