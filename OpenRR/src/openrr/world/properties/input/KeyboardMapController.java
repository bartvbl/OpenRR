package openrr.world.properties.input;

import openrr.camera.MapCamera;
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

	public KeyboardMapController(GameObject gameObject) {
		super(ORRPropertyType.KEYBOARD_MAP_CONTROLLER, gameObject);
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
			enableMapRotation = true;
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
		
		camera.translate(0, 0, zoomDelta / 35d);
		
		double mapX = camera.getX();
		double mapY = camera.getY();
		
		camera.setMapHeight(map.getTileHeightAt(mapX, mapY));
		
		mapDX = 0;
		mapDY = 0;
		mouseDX = 0;
		mouseDY = 0;
		zoomDelta = 0;
		enableMapRotation = false;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {
		int mapID = this.gameObject.world.getOnlyGameObject(ORRGameObjectType.MAP);
		this.map = (MapTileReader)this.gameObject.world.requestPropertyData(mapID, ORRPropertyDataType.MAP_TILES, null, MapTileReader.class);
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapLeft");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapRight");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapUp");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "moveMapDown");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "zoomMap");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "enableMapRotation");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "rotateMap");
		this.gameObject.world.services.inputService.addCommandListener(this.gameObject.id, "tiltMap");
		camera = new MapCamera();
		camera.translate(0, 0, 30);
		gameObject.world.services.cameraService.activateCamera(camera);
	}

}
