package openrr.map.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import openrr.map.MapTile;
import openrr.world.core.ORRResourceType;
import nu.xom.Document;
import orre.resources.Resource;
import orre.resources.ResourceQueue;
import orre.resources.ResourceTypeLoader;
import orre.util.XMLLoader;

public class MapLoader implements ResourceTypeLoader {
	
	@Override
	public Map readResource(Resource source) throws Exception {
		return loadMap(source);
	}
	
	@Override
	public Enum<?> getResourceType() {
		return ORRResourceType.map;
	}

	public static IncompleteMap loadMap(Resource resource) throws Exception {
		ZipFile mapFile = openMap(resource.fileLocation);
		Document mapXML = readMapXML(mapFile);
		MapLoadingContext context = new MapLoadingContext(mapXML, mapFile);
		IncompleteMap map = parseMapXML(context);
		return map;
	}
	
	private static ZipFile openMap(File src) throws IOException {
		ZipFile mapFile = new ZipFile(src);
		return mapFile;
	} 
	
	private static Document readMapXML(ZipFile mapFile) throws IOException {
		ZipEntry mainMapXMLEntry = mapFile.getEntry("map.xml");
		InputStream inputStream = mapFile.getInputStream(mainMapXMLEntry);
		return XMLLoader.readXML(inputStream);
	}

	private static IncompleteMap parseMapXML(MapLoadingContext context) throws Exception {
		MapTexturePack texturePack = MapTexturePackLoader.buildTexturePack(context);
		context.texturePack = texturePack;
		MapTile[][] tileMap = TileMapLoader.loadTileMap(context);
		return new IncompleteMap(tileMap, texturePack);
	}

}
