package openrr.world.buildings;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
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
	protected void initAppearance() {
		appearance.root.setLocation(3.5, 3.5, 0);
	}

	@Override
	protected void placeAppearanceInScene() {
		SceneNode mapRoot = MapWorldUtils.getMapRoot(gameObject.world);
		mapRoot.addChild(this.appearance.root);
	}

}