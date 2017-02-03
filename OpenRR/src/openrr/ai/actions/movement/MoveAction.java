package openrr.ai.actions.movement;

import openrr.ai.MapTileNode;
import openrr.animation.AnimationType;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import orre.ai.pathFinding.AStar;
import orre.ai.pathFinding.Path;
import orre.ai.tasks.Action;
import orre.animation.AnimationBehaviour;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point2D;
import orre.geom.mesh.Model;

public class MoveAction extends Action {

	private static final AStar astar = new AStar();

	private boolean hasFinished = false;
	private MapTileNode nextNode;
	
	private final Model target;
	private final GameWorld world;
	private final double movementSpeed;

	private AnimationType moveAnimation;
 
	
	public static MoveAction plan(int targetID, Point2D start, Point2D destination, GameWorld world, AnimationType moveAnimationType, int goalRangeTiles) {
		int mapID = world.getAllGameObjectsByType(ORRGameObjectType.MAP)[0];
		
		MapTileReader reader = (MapTileReader) world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		MapTileNode unitLocation = new MapTileNode(reader, start.x, start.y, goalRangeTiles);
		MapTileNode taskLocation = new MapTileNode(reader, destination.x, destination.y, goalRangeTiles);
		Path pathToTask = astar.findPath(unitLocation, taskLocation);
		
		Model rootNode = (Model) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Model.class);
		
		return new MoveAction(pathToTask, targetID, rootNode, world, moveAnimationType);
	}
	
	private MoveAction(Path pathToTask, int targetID, Model target, GameWorld world, AnimationType animation) {
		this.path = pathToTask;
		this.target = target;
		this.world = world;
		this.movementSpeed = (double)world.requestPropertyData(targetID, ORRPropertyDataType.MOVEMENT_SPEED_SOIL, (Double)1d, Double.class);
		this.nextNode = (MapTileNode) path.getStartingState();
		this.moveAnimation = animation;
	}
	
	private final Path path;
	private int walkingAnimationID;

	@Override
	public boolean isExecutionPossible() {
		return path.isFoundPath;
	}

	@Override
	public void update() {
		if(!hasFinished) {
			double angle = Math.atan2(nextNode.y - target.getRootNode().getY(), nextNode.x - target.getRootNode().getX());
			double dx = Math.cos(angle)*movementSpeed;
			double dy = Math.sin(angle)*movementSpeed;
			
			this.target.getRootNode().translate(dx, dy, 0);
			this.target.getRootNode().setRotationZ(Math.toDegrees(angle) + 90);
		}
		
		double dx = target.getRootNode().getX() - nextNode.x;
		double dy = target.getRootNode().getY() - nextNode.y;
		double distanceToTarget = Math.sqrt(dx*dx + dy*dy);
		
		if(distanceToTarget < 1.1 * movementSpeed) {
			if(path.hasFinished()) {
				this.hasFinished = true;
			} else {
				this.nextNode = (MapTileNode) path.getNextState();
			}
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

	@Override
	public void start() {
		this.walkingAnimationID = this.world.services.animationService.applyAnimation(moveAnimation.toString(), target, AnimationBehaviour.REPEAT_ON_COMPLETE);
	}

	@Override
	public void end() {
		this.world.services.animationService.stopAnimation(walkingAnimationID);
	}
}
