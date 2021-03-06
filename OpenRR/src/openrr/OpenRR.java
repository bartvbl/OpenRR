package openrr;

import openrr.map.loader.MapLoader;
import orre.api.GameSettings;
import orre.api.ORRE;

public class OpenRR {
	public static void main(String[] args) {
		OpenRRPropertyProvider provider = new OpenRRPropertyProvider();
		GameSettings settings = new GameSettings("Terrain", provider);
		ORRE engine = ORRE.init(args, settings);
		engine.registerResourceLoader(new MapLoader(), new String[]{".rrm"});
		engine.run();
	}
}