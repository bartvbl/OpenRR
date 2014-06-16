package openrr.world.buildings;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.PropertyDataType;
import orre.gameWorld.properties.Appearance;
import orre.sceneGraph.SceneNode;

public class ToolStoreAppearance extends Appearance {

	public ToolStoreAppearance(GameObject gameObject) {
		super(ORRPropertyType.TOOL_STORE_APPEARANCE, "toolStore", gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	protected void initAppearance() {
	}

	@Override
	protected void placeAppearanceInScene() {
		int mapID = this.gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		SceneNode mapRoot = (SceneNode) this.gameObject.world.requestPropertyData(mapID, PropertyDataType.APPEARANCE, null, SceneNode.class);
		mapRoot.addChild(this.appearance.root);
	}

}
