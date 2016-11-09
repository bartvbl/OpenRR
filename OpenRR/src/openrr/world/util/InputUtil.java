package openrr.world.util;

import org.lwjgl.util.vector.Vector3f;

import orre.gameWorld.core.GameWorld;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;

public class InputUtil {
	public static Vector3f getMouseLocation(GameWorld world) {
		int mouseProbeID = world.getOnlyGameObject(ORRGameObjectType.MOUSE_TRACKER);
		return (Vector3f) world.requestPropertyData(mouseProbeID, ORRPropertyDataType.MOUSE_LOCATION, null, Vector3f.class);
	}
}
