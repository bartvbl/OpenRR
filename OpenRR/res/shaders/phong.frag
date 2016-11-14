#version 450

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 texCoord;
layout(location = 2) in vec4 normal;

out vec4 colour;

layout(location = 3) uniform sampler2D diffuseTexture;
layout(location = 7) uniform float texturesEnabled;

layout(location = 14) uniform vec4 light_position;
layout(location = 15) uniform vec4 light_ambient;
layout(location = 16) uniform vec4 light_diffuse;
layout(location = 17) uniform vec4 light_specular;

layout(location = 19) uniform vec4 material_ambient;
layout(location = 20) uniform vec4 material_diffuse;
layout(location = 21) uniform vec4 material_specular;
layout(location = 22) uniform vec4 material_emission;
layout(location = 23) uniform float material_shininess;

vec4 lighting()
{
	vec4 norm = normalize(normal);

	vec3 lightVector = normalize(light_position.xyz - position.xyz);
	vec3 reflection = normalize(lightVector - normalize(position.xyz));

	float diffuseFactor = max(0.0, dot(norm.xyz, lightVector));
	float specularDot = max(0.0, dot(norm.xyz, reflection));

	float specularFactor = pow(specularDot, material_shininess);
	
	vec4 ambient = vec4(vec3(material_ambient.rgb * material_ambient.rgb), 0.0);
	vec4 diffuse = vec4(vec3(material_diffuse.rgb * material_diffuse.rgb * diffuseFactor), material_diffuse.a);
	vec4 specular = vec4(vec3(material_specular.rgb * material_specular.rgb * specularFactor), 0.0);
	vec4 emission = vec4(material_emission.rgb, 0.0);

	return ambient + diffuse + specular + emission;
}

void main()
{
	vec4 lightValue = lighting();
	colour = (texturesEnabled * texture2D(diffuseTexture, texCoord) * lightValue) + ((1.0 - texturesEnabled) * lightValue);
}