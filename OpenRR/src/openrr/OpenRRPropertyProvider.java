package openrr;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.api.PropertyTypeProvider;
import orre.gameWorld.core.Property;


public class OpenRRPropertyProvider implements PropertyTypeProvider {

	@Override
	public Enum<?>[] getGameObjectTypes() {
		return ORRGameObjectType.values();
	}

	@Override
	public Enum<?>[] getPropertyTypes() {
		return ORRPropertyType.values();
	}

	@Override
	public Enum<?>[] getProperties(Enum<?> gameSpecificObjectType) {
		return ((ORRGameObjectType)gameSpecificObjectType).properties;
	}

	@Override
	public Class<? extends Property> getPropertyClass(Enum<?> type) {
		return ((ORRPropertyType)type).propertyClass;
	}

	@Override
	public Class<?> getRequiredDataType(Enum<?> dataType) {
		return ((ORRPropertyDataType)dataType).expectedReturnDataType;
	}
}
