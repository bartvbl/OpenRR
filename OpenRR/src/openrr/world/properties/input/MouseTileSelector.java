package openrr.world.properties.input;

import java.util.Arrays;

import org.lwjgl.util.vector.Vector3f;

import openrr.input.InputPriority;
import openrr.map.MapTile;
import openrr.map.world.MapTileReader;
import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
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
	private int selectionX;
	private int selectionY;
	private MapTileSelectionNode selectionNode;
	private MapTileReader reader;

	public MouseTileSelector(GameObject gameObject) {
		super(ORRPropertyType.MOUSE_TILE_SELECTOR, gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		if(message.type == MessageType.INPUT_EVENT) {
			InputEvent event = (InputEvent) message.getPayload();
			if(event.command.equals("select")) {
				mouseState = event.value == 1.0;
				if((mouseState == true) && (wasMouseDown == false)) {
					Vector3f mouseLocation = InputUtil.getMouseLocation(gameObject.world);
					
					selectionX = (int) Math.floor(mouseLocation.x + 0.5);
					selectionY = (int) Math.floor(mouseLocation.y + 0.5);
					
					MapTile tile = reader.getTileAt(selectionX, selectionY);

					selectionNode.update(selectionX, selectionY, tile);
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
		gameObject.world.services.inputService.addCommandListener(gameObject.id, "select", InputPriority.MOUSE_SELECT_TILE.priority);
		SceneNode mapRootNode = MapWorldUtils.getMapRoot(gameObject.world);
		int mapID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		this.selectionNode = new MapTileSelectionNode(reader);
		mapRootNode.addChild(selectionNode);
	}

}
