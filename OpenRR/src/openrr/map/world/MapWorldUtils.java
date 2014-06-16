package openrr.map.world;

import openrr.world.core.ORRGameObjectType;
import orre.gameWorld.core.GameWorld;
import orre.gameWorld.core.PropertyDataType;
import orre.geom.mesh.Mesh3D;
import orre.sceneGraph.SceneNode;

public class MapWorldUtils {
	public static SceneNode getMapRoot(GameWorld world) {
		int mapID = world.getOnlyGameObject(ORRGameObjectType.MAP);
		return ((Mesh3D) world.requestPropertyData(mapID, PropertyDataType.APPEARANCE, null, Mesh3D.class)).root;
	}
}
