package openrr.world.buildings.superTeleport;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.properties.Appearance;
import orre.resources.ResourceType;
import orre.sceneGraph.SceneNode;

public class SuperTeleportAppearance extends Appearance {
	public SuperTeleportAppearance(GameObject gameObject) {
		super(ORRPropertyType.SUPER_TELEPORT_APPEARANCE, ResourceType.lxfmlModel, "superTeleport", gameObject);
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