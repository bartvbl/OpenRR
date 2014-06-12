package openrr;

import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.api.PropertyTypeProvider;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;


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
		if(dataType instanceof PropertyDataType) {
			return ((PropertyDataType)dataType).expectedReturnDataType;
		}
		return ((ORRPropertyDataType)dataType).expectedReturnDataType;
	}

	@Override
	public Enum<?> getGameObjectTypeFromString(String gameObjectType) {
		return ORRGameObjectType.valueOf(gameObjectType);
	}
}
