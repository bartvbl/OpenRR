#version 450

in layout(location=4) vec4 position;
in layout(location=5) vec2 textureCoordinate;
in layout(location=6) vec4 normal;

uniform layout(location=0) sampler2D diffuseTexture;
uniform layout(location=1) float texturesEnabled;

uniform layout(location=2) vec4 lightPosition;

uniform layout(location=3) vec4 material_ambient;
uniform layout(location=4) vec4 material_diffuse;
uniform layout(location=5) vec4 material_specular;
uniform layout(location=6) vec4 material_emission;

uniform layout(location=7) float material_shininess;

uniform layout(location=8) vec4 light_ambient;
uniform layout(location=9) vec4 light_diffuse;
uniform layout(location=10) vec4 light_specular;

out vec4 colour;

vec4 lightSource(vec3 norm, vec3 view)
{
	vec3 lightVector = normalize(lightPosition.xyz - view);
	vec3 reflection = normalize(lightVector - normalize(view.xyz));

	float diffuseFactor = max(0.0, dot(norm, lightVector));
	float specularDot = max(0.0, dot(norm, reflection));

	float specularFactor = pow(specularDot, material_shininess);
	
	vec4 ambient = vec4(vec3(material_ambient.rgb * light_ambient.rgb), 0.0);
	vec4 diffuse = vec4(vec3(material_diffuse.rgb * light_diffuse.rgb * diffuseFactor), material_diffuse.a);
	vec4 specular = vec4(vec3(material_specular.rgb * light_specular.rgb * specularFactor), 0.0);
	vec4 emission = vec4(material_emission.rgb, 0.0);
	
	return emission + ambient + diffuse + specular;
}

vec4 lighting()
{
	// normal might be damaged by linear interpolation.
	vec4 norm = normalize(normal);

	return lightSource(norm.xyz, position.xyz);
}

void main()
{
	vec4 lightValue = lighting();
	colour = (texturesEnabled * texture2D(diffuseTexture, textureCoordinate.st) * lightValue) + ((1.0 - texturesEnabled) * lightValue) * vec4(0, 1, 1, 1);
}