package openrr.map.soil;

import orre.gl.materials.Material;
import orre.gl.renderer.RenderState;
import orre.gl.renderer.ShaderProperty;
import orre.gl.texture.Texture;
import orre.resources.incompleteResources.IncompleteTexture;

public class MapTexture {
	public final String name;
	public final int widthInTextures;
	public final int heightInTextures;
	private IncompleteTexture partiallyLoadedTexture;
	private Texture texture;

	public MapTexture(String name, IncompleteTexture texture, int widthInTextures, int heightInTextures) {
		this.name = name;
		this.partiallyLoadedTexture = texture;
		this.widthInTextures = widthInTextures;
		this.heightInTextures = heightInTextures;
	}
	
	public void finalizeTexture() {
		this.partiallyLoadedTexture.finalizeResource();
		this.texture = this.partiallyLoadedTexture.getTexture();
	}
	
	public void bind(RenderState state) {
		state.shaders.setPropertyi(ShaderProperty.TEXTURE0, texture.id);
	}

	public Material generateTextureMaterial() {
		Material material = new Material("map texture material");
		material.setDiffuseTexture(texture);
		material.setAmbientColour(new float[]{0.2f, 0.2f, 0.2f, 1});
		material.setDiffuseColour(new float[]{0.8f, 0.8f, 0.8f, 1});
		material.setSpecularColour(new float[]{1, 1, 1, 1});
		material.setShininess(75f);
		return material;
	}
}
