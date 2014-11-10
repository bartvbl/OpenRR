package openrr.ai.actions.movement;

import org.lwjgl.util.vector.Vector3f;

import openrr.ai.MapTileNode;
import openrr.ai.taskRequests.MapTaskRequest;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.pathFinding.Path;
import orre.ai.tasks.Action;
import orre.ai.tasks.TaskRequest;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point2D;
import orre.geom.mesh.Mesh3D;
import orre.geom.mesh.Model;
import orre.sceneGraph.CoordinateNode;

public class MoveAction extends Action {

	private static final AStar astar = new AStar();

	private boolean hasStarted = false;
	private boolean hasFinished = false;
	private MapTileNode nextNode;
	
	private final Mesh3D target;
	private final GameWorld world;
	private final double movementSpeed;

	
	public static MoveAction plan(int targetID, Point2D start, Point2D destination, GameWorld world) {
		int mapID = world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		
		MapTileReader reader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		MapTileNode unitLocation = new MapTileNode(reader, start.x, start.y);
		MapTileNode taskLocation = new MapTileNode(reader, destination.x, destination.y);
		Path pathToTask = astar.findPath(unitLocation, taskLocation);
		
		Mesh3D rootNode = (Mesh3D) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Mesh3D.class);
		
		return new MoveAction(pathToTask, targetID, rootNode, world);
	}
	
	private MoveAction(Path pathToTask, int targetID, Mesh3D target, GameWorld world) {
		this.path = pathToTask;
		this.target = target;
		this.world = world;
		this.movementSpeed = (double)world.requestPropertyData(targetID, ORRPropertyDataType.MOVEMENT_SPEED_SOIL, (Double)1d, Double.class);
		this.nextNode = (MapTileNode) path.getStartingState();
	}
	
	private final Path path;
	private int walkingAnimationID;

	@Override
	public boolean isExecutionPossible() {
		return path.isFoundPath;
	}

	@Override
	public void update() {
		if(!hasStarted) {
			this.walkingAnimationID = this.world.services.animationService.applyAnimation(AnimationType.raiderWalking, target);
		}
		
		if(!hasFinished) {
			double angle = Math.atan2(nextNode.y - target.root.getY(), nextNode.x - target.root.getX());
			double dx = Math.cos(angle)*movementSpeed;
			double dy = Math.sin(angle)*movementSpeed;
			
			this.target.root.translate(dx, dy, 0);
			this.target.root.setRotationZ(Math.toDegrees(angle) + 90);
		}
		
		
		double dx = target.root.getX() - nextNode.x;
		double dy = target.root.getY() - nextNode.y;
		double distanceToTarget = Math.sqrt(dx*dx + dy*dy);
		
		if(distanceToTarget < 1.1 * movementSpeed) {
			if(path.hasFinished()) {
				this.hasFinished = true;
			} else {
				this.nextNode = (MapTileNode) path.getNextState();
			}
		}
		
		if(this.hasFinished) {
			this.world.services.animationService.stopAnimation(walkingAnimationID);
		}
	}

	@Override
	public boolean isFinished() {
		return hasFinished;
	}

	@Override
	public double getCost() {
		return path.getStepCount();
	}
	
}
