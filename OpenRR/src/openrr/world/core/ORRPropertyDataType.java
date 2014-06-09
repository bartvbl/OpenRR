package openrr.world.core;

public enum ORRPropertyDataType {
	;
	
	public final Class<?> expectedReturnDataType;

	private ORRPropertyDataType(Class<?> dataType) {
		this.expectedReturnDataType = dataType;
	}
}
