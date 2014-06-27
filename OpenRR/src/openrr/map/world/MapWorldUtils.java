package openrr.map.world;

import openrr.world.core.ORRGameObjectType;
import orre.animation.Animatable;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Mesh3D;
import orre.geom.mesh.Model;
import orre.sceneGraph.SceneNode;

public class MapWorldUtils {
	public static SceneNode getMapRoot(GameWorld world) {
		int mapID = world.getOnlyGameObject(ORRGameObjectType.MAP);
		return ((Model) world.requestPropertyData(mapID, PropertyDataType.APPEARANCE, null, Animatable.class)).getRootNode();
	}
}
