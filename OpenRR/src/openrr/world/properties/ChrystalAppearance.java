package openrr.world.properties;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.properties.Appearance;
import orre.sceneGraph.SceneNode;

public class ChrystalAppearance extends Appearance {

	public ChrystalAppearance(GameObject gameObject) {
		super(ORRPropertyType.CHRYSTAL_APPEARANCE, "chrystal", gameObject);
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
		
	}

	@Override
	protected void placeAppearanceInScene() {
		SceneNode mapRoot = MapWorldUtils.getMapRoot(gameObject.world);
		mapRoot.addChild(this.appearance.root);
	}

}
