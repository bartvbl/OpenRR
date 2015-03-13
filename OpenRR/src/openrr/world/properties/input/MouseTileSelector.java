package openrr.world.properties.input;

import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.Property;
import orre.input.InputEvent;

public class MouseTileSelector extends Property {
	
	private boolean wasMouseDown;
	private boolean mouseState;

	public MouseTileSelector(GameObject gameObject) {
		super(ORRPropertyType.MOUSE_TILE_SELECTOR, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		if(message.type == MessageType.INPUT_EVENT) {
			InputEvent event = (InputEvent) message.getPayload();
			if(event.command.equals("select")) {
				mouseState = event.value == 1.0;
				if((mouseState == false) && (wasMouseDown == true)) {
					
					System.out.println("Clicked map!");
				}
				wasMouseDown = mouseState;
			}
		}
	}

	@Override
	public void tick() {
		//unused
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {
		gameObject.world.services.inputService.addCommandListener(gameObject.id, "select");
	}

}
