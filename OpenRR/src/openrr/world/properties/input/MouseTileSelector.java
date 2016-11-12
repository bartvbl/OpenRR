package openrr.world.properties.input;

import org.lwjgl.util.vector.Vector3f;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import openrr.world.util.InputUtil;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.Property;
import orre.input.InputEvent;
import orre.sceneGraph.SceneNode;

public class MouseTileSelector extends Property {
	
	private boolean wasMouseDown;
	private boolean mouseState;
	private boolean isSelectionActive;
	private int selectionX;
	private int selectionY;
	private MapTileSelectionNode selectionNode;

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
					Vector3f mouseLocation = InputUtil.getMouseLocation(gameObject.world);
					
					selectionX = (int) Math.floor(mouseLocation.x + 0.5);
					selectionY = (int) Math.floor(mouseLocation.y + 0.5);
					isSelectionActive = true;
					
					selectionNode.update(selectionX, selectionY);
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
		SceneNode mapRootNode = MapWorldUtils.getMapRoot(gameObject.world);
		mapRootNode.removeChild(selectionNode);
	}

	@Override
	public void init() {
		gameObject.world.services.inputService.addCommandListener(gameObject.id, "select");
		SceneNode mapRootNode = MapWorldUtils.getMapRoot(gameObject.world);
		this.selectionNode = new MapTileSelectionNode();
		mapRootNode.addChild(selectionNode);
	}

}
