package openrr.world.properties.input;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.sceneGraph.CoordinateNode;

public class KeyboardMapController extends Property {

	private CoordinateNode target;

	public KeyboardMapController(GameObject gameObject) {
		super(ORRPropertyType.KEYBOARD_MAP_CONTROLLER, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {

	}

	@Override
	public void tick() {
		this.target.setX((Mouse.getX() - Display.getWidth()/2)*0.2);
		this.target.setY(-30);
		this.target.setZ((-Mouse.getY())*0.2);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {
		int mapID = this.gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.target = (CoordinateNode)this.gameObject.world.requestPropertyData(mapID, PropertyDataType.APPEARANCE, null, CoordinateNode.class);
	}

}
