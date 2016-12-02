package openrr.input;

public enum InputPriority {
	// Note: GUI always has priority 0
	
	PLACE_BUILDING_CONFIRM(1), 
	PLACE_BUILDING_ABORT(2), 
	CAMERA_MOVE_LEFT(3), 
	CAMERA_MOVE_RIGHT(4), 
	CAMERA_MOVE_UP(5), 
	CAMERA_MOVE_DOWN(6), 
	CAMERA_ZOOM(7), 
	CAMERA_ENABLE_ROTATION(8), 
	CAMERA_ROTATE_VERTICAL(9), 
	CAMERA_ROTATE_HORIZONTAL(10), 
	MOUSE_MOVE_X(11), 
	MOUSE_MOVE_Y(12), 
	MOUSE_SELECT_TILE(13)
	;
	
	public final int priority;

	private InputPriority(int priority) {
		this.priority = priority;
	}
}
