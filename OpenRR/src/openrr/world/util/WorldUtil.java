package openrr.world.util;

import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.Point2D;
import orre.geom.mesh.Model;
import orre.sceneGraph.CoordinateNode;

public class WorldUtil {

	public static CoordinateNode getRootNode(int targetID, GameWorld world) {
		return ((Model) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Model.class)).getRootNode();
	}
	
	public static Point2D getTargetLocation(int targetID, GameWorld world) {
		Model appearance = (Model) world.requestPropertyData(targetID, PropertyDataType.APPEARANCE, null, Model.class);
		return new Point2D((int)appearance.getRootNode().getX(), (int) appearance.getRootNode().getY());
	}

}
