#version 450

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 texCoord;

out vec4 colour;

layout(location = 3) uniform sampler2D diffuseTexture;
layout(location = 5) uniform float texturesEnabled;

layout(location = 14) uniform vec4 light_position;
layout(location = 15) uniform vec4 light_ambient;
layout(location = 16) uniform vec4 light_diffuse;
layout(location = 17) uniform vec4 light_specular;

layout(location = 19) uniform vec4 material_ambient;
layout(location = 20) uniform vec4 material_diffuse;
layout(location = 21) uniform vec4 material_specular;
layout(location = 22) uniform vec4 material_emission;
layout(location = 23) uniform float material_shininess;

void main()
{
	colour = texture2D(diffuseTexture, texCoord);
}