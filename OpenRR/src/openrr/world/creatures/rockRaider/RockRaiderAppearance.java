package openrr.world.creatures.rockRaider;

import openrr.map.world.MapWorldUtils;
import openrr.world.core.ORRPropertyType;
import orre.animation.Animatable;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.PropertyType;
import orre.gameWorld.properties.Appearance;
import orre.resources.ResourceType;
import orre.resources.data.OBJBlueprintModel;
import orre.sceneGraph.SceneNode;

public class RockRaiderAppearance extends Appearance {

	public RockRaiderAppearance(GameObject gameObject) {
		super(ORRPropertyType.ROCK_RAIDER_APPEARANCE, ResourceType.model, "rockRaider", gameObject);
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
		appearance.getRootNode().setLocation(2.5, 2.5, 0);
		appearance.getRootNode().rotate(0, 0, 180);
		gameObject.world.services.animationService.applyAnimation(AnimationType.raiderWalking, appearance);
	}

	@Override
	protected void placeAppearanceInScene() {
		SceneNode mapRoot = MapWorldUtils.getMapRoot(gameObject.world);
		mapRoot.addChild(this.appearance.getRootNode());
	}
}
