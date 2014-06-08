package openrr.world.properties;

import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.PropertyType;
import orre.gameWorld.properties.Appearance;

public class ChrystalAppearance extends Appearance {

	public ChrystalAppearance(GameObject gameObject) {
		super(ORRPropertyType.CHRYSTAL_APPEARANCE.toString(), "chrystal", gameObject);
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

}
