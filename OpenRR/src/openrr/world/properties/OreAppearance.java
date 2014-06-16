package openrr.world.properties;

import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.PropertyType;
import orre.gameWorld.properties.Appearance;

public class OreAppearance extends Appearance {
	public OreAppearance(GameObject gameObject) {
		super(ORRPropertyType.ORE_APPEARANCE, "ore", gameObject);
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
		this.appearance.root.setLocation(48.5, 50.5, 0);
	}

	@Override
	protected void placeAppearanceInScene() {
		
	}

}
