package openrr.world.buildings.upgradeStation;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.properties.Appearance;
import orre.resources.ResourceType;
import orre.sceneGraph.SceneNode;

public class UpgradeStationAppearance extends Appearance {

	public UpgradeStationAppearance(GameObject gameObject) {
		super(ORRPropertyType.UPGRADE_STATION_APPEARANCE, ResourceType.lxfmlModel, "upgradeStation", gameObject);
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
		mapRoot.addChild(this.appearance.getRootNode());
	}

}
