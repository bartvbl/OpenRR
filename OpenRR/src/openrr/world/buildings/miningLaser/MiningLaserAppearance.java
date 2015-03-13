package openrr.world.buildings.miningLaser;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.properties.Appearance;
import orre.resources.ResourceType;
import orre.sceneGraph.SceneNode;

public class MiningLaserAppearance extends Appearance {
	public MiningLaserAppearance(GameObject gameObject) {
		super(ORRPropertyType.MINING_LASER_APPEARANCE, ResourceType.lxfmlModel, "miningLaser", gameObject);
	}

	@Override
	public void handleMessage(Message<?> message) {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	protected void initAppearance() {
	}

	@Override
	protected void placeAppearanceInScene() {
		SceneNode mapRoot = MapWorldUtils.getMapRoot(gameObject.world);
		mapRoot.addChild(this.appearance.getRootNode());
	}

	@Override
	protected void removeAppearenceFromScene() {
		SceneNode mapRoot = MapWorldUtils.getMapRoot(gameObject.world);
		mapRoot.removeChild(this.appearance.getRootNode());
	}
}
