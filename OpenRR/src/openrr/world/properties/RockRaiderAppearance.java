package openrr.world.properties;

import openrr.world.core.ORRPropertyType;
import orre.animation.AnimationType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.PropertyType;
import orre.gameWorld.properties.Appearance;

public class RockRaiderAppearance extends Appearance {

	public RockRaiderAppearance(GameObject gameObject) {
		super(ORRPropertyType.ROCK_RAIDER_APPEARANCE, "rockRaider", gameObject);
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
		appearance.root.setLocation(2.5, 2.5, 0);
		appearance.root.rotate(0, 0, 180);
		gameObject.world.services.animationService.applyAnimation(AnimationType.raiderWalking, appearance);
	}
}