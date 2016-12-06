package openrr.world.properties.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

import openrr.ai.tasks.BlastTask;
import openrr.ai.tasks.DrillTask;
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
import orre.gameWorld.core.MessageHandler;
import orre.gameWorld.core.MessageType;
import orre.gameWorld.core.Property;
import orre.geom.MutableIndex2D;
import orre.input.InputEvent;
import orre.sceneGraph.SceneNode;
import orre.scripting.ScriptEvent;

public class MouseTileSelector extends Property implements MessageHandler {
	
	private MutableIndex2D selectionLocation;
	private MapTileSelectionNode selectionNode;
	private MapTileReader reader;
	
	private ArrayList<DrillTask> drillTasks = new ArrayList<DrillTask>(); 
	private ArrayList<BlastTask> blastTasks = new ArrayList<BlastTask>(); 

	public MouseTileSelector(GameObject gameObject) {
		super(ORRPropertyType.MOUSE_TILE_SELECTOR, gameObject);
		gameObject.world.services.scriptingService.getClass();
	}

	// TODO: Handle menu switch and enable/disable specific buttons
	
	@Override
	public void handleMessage(Message<?> message) {
		if(message.type == MessageType.INPUT_EVENT) {
			InputEvent event = (InputEvent) message.getPayload();
			if(event.command.equals("select")) {
				if(event.delta == 1.0) {
					Vector3f mouseLocation = InputUtil.getMouseLocation(gameObject.world);
					
					selectionLocation.x = (int) Math.floor(mouseLocation.x + 0.5);
					selectionLocation.y = (int) Math.floor(mouseLocation.y + 0.5);
					
					MapTile tile = reader.getTileAt(selectionLocation.x, selectionLocation.y);

					selectionNode.update(selectionLocation.x, selectionLocation.y, tile);
					
					HashMap<String, String> parameters = new HashMap<String, String>();
					
					if(tile.isWall()) {
						parameters.put("action", "wall");						
					} else {
						parameters.put("action", "floor");
					}
					
					gameObject.world.services.scriptingService.dispatchScriptEvent("showTileSelectionMenu", parameters);
				}
			}
		}
		if(message.type == MessageType.SCRIPT_EVENT) {
			ScriptEvent event = (ScriptEvent) message.getPayload();
			if(event.type.equals("drillSelectedWall")) {
				selectionLocation.x = -1;
				selectionLocation.y = -1;
				selectionNode.hide();
				
				DrillTask drillTask = new DrillTask(gameObject.id, selectionLocation.asImmutable());
				drillTasks.add(drillTask);
				gameObject.world.services.aiService.registerTask(drillTask);
			}
			if(event.type.equals("blastSelectedWall")) {
				selectionLocation.x = -1;
				selectionLocation.y = -1;
				selectionNode.hide();
				
				BlastTask blastTask = new BlastTask(gameObject.id, selectionLocation.asImmutable());
				blastTasks.add(blastTask);
				gameObject.world.services.aiService.registerTask(blastTask);
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
		gameObject.world.addMessageListener(MessageType.SCRIPT_EVENT, this);
		SceneNode mapRootNode = MapWorldUtils.getMapRoot(gameObject.world);
		int mapID = gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.reader = (MapTileReader) gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		this.selectionNode = new MapTileSelectionNode(reader);
		mapRootNode.addChild(selectionNode);
	}

}
