package openrr.world.properties.input;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.input.InputEvent;

public class MouseProbeTracker extends Property {

	private MouseProbeNode mouseProbe;
	private double mouseX;
	private double mouseY;

	public MouseProbeTracker(GameObject gameObject) {
		super(ORRPropertyType.MOUSE_PROBE_TRACKER, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		InputEvent event = (InputEvent) message.getPayload();
		if(event.command.equals("mouseMovedX")) {
			mouseX = event.value;
		} else if(event.command.equals("mouseMovedY")) {
			mouseY = event.value;
		}
	}

	@Override
	public void tick() {
		float[] mouseCoords = mouseProbe.getMapCoordinates();
		this.gameObject.setPropertyData(ORRPropertyDataType.MOUSE_LOCATION, new Vector3f(mouseCoords[0], mouseCoords[1], mouseCoords[2]));
		this.mouseProbe.setMouseCoordinates(mouseX, mouseY);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		this.mouseProbe = new MouseProbeNode();
		MapWorldUtils.getMapRoot(gameObject.world).addChild(mouseProbe);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "mouseMovedX");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "mouseMovedY");
	}

}
