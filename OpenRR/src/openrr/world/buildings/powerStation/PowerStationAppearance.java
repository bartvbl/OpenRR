package openrr.world.buildings.powerStation;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.properties.Appearance;
import orre.resources.ResourceType;
import orre.sceneGraph.SceneNode;

public class PowerStationAppearance extends Appearance {
	public PowerStationAppearance(GameObject gameObject) {
		super(ORRPropertyType.POWER_STATION_APPEARANCE, ResourceType.lxfmlModel, "powerStation", gameObject);
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
