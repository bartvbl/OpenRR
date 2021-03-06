package openrr.world.properties.input;

import openrr.camera.MapCamera;
import openrr.input.InputPriority;
import openrr.map.world.MapTileReader;
import openrr.world.core.ORRGameObjectType;
import openrr.world.core.ORRPropertyDataType;
import openrr.world.core.ORRPropertyType;
import orre.gameWorld.core.GameObject;
import orre.gameWorld.core.Message;
import orre.gameWorld.core.Property;
import orre.gameWorld.core.PropertyDataType;
import orre.input.InputEvent;
import orre.sceneGraph.CoordinateNode;

public class KeyboardMapController extends Property {

	private MapCamera camera;
	private double mapDX;
	private double mapDY;
	
	private double mouseDX;
	private double mouseDY;
	
	private double zoomDelta;
	private boolean enableMapRotation;
	private MapTileReader map;
	
	private static final double mapMoveSpeed = 0.3d;
	private static final double zoomSlowdownFactor = 75;

	public KeyboardMapController(GameObject gameObject) {
		super(ORRPropertyType.KEYBOARD_MAP_CONTROLLER, gameObject, true);
	}

	@Override
	public void handleMessage(Message<?> message) {
		InputEvent event = (InputEvent) message.getPayload();
		if(event.command.equals("moveMapRight")) {
			mapDX += event.value;
		} else if(event.command.equals("moveMapLeft")) {
			mapDX -= event.value;
		} else if(event.command.equals("moveMapUp")) {
			mapDY += event.value;
		} else if(event.command.equals("moveMapDown")) {
			mapDY -= event.value;
		} else if(event.command.equals("zoomMap")) {
			zoomDelta += event.delta;
		} else if(event.command.equals("enableMapRotation")) {
			enableMapRotation = event.delta == 1;
		} else if(event.command.equals("rotateMap")) {
			mouseDX += event.delta;
		} else if(event.command.equals("tiltMap")) {
			mouseDY += event.delta;
		}
	}

	@Override
	public void tick() {
		if(enableMapRotation) {
			camera.rotate(-mouseDY / 4d, 0, mouseDX / 4d);
		}

		if((mapDX != 0) || (mapDY != 0)) {
			double moveDirection = Math.atan2(mapDX, mapDY);
			moveDirection -= Math.toRadians(camera.getRotationZ());
			camera.translate(Math.sin(moveDirection) * mapMoveSpeed, Math.cos(moveDirection) * mapMoveSpeed,  0);
		}
		
		camera.translate(0, 0, zoomDelta / zoomSlowdownFactor);
		
		double mapX = camera.getX();
		double mapY = camera.getY();
		
		camera.setMapHeight(map.getTileHeightAt(mapX, mapY));
		
		mapDX = 0;
		mapDY = 0;
		mouseDX = 0;
		mouseDY = 0;
		zoomDelta = 0;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {
		int mapID = this.gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.map = (MapTileReader)this.gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapLeft", InputPriority.CAMERA_MOVE_LEFT.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapRight", InputPriority.CAMERA_MOVE_RIGHT.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapUp", InputPriority.CAMERA_MOVE_UP.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapDown", InputPriority.CAMERA_MOVE_DOWN.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "zoomMap", InputPriority.CAMERA_ZOOM.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "enableMapRotation", InputPriority.CAMERA_ENABLE_ROTATION.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "rotateMap", InputPriority.CAMERA_ROTATE_VERTICAL.priority);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "tiltMap", InputPriority.CAMERA_ROTATE_HORIZONTAL.priority);
		camera = (MapCamera) this.gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_CAMERA, null, MapCamera.class);
		camera.translate(0, 0, 30);
	}

}
