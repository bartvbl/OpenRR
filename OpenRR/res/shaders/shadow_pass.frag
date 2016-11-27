#version 450

in layout(location = 0) vec4 location;

out layout(location = 0) float depth;

layout(location = 3) uniform sampler2D diffuseTexture;
layout(location = 40) uniform sampler2D depthTexture;

layout(location = 5) uniform float texturesEnabled;

layout(location = 12) uniform vec4 camera_position;

layout(location = 14) uniform vec4 light_position;
layout(location = 15) uniform vec4 light_ambient;
layout(location = 16) uniform vec4 light_diffuse;
layout(location = 17) uniform vec4 light_specular;
layout(location = 18) uniform float light_specular_strength;

layout(location = 24) uniform float attenuation_constant;
layout(location = 25) uniform float attenuation_linear;
layout(location = 26) uniform float attenuation_quadratic;

layout(location = 19) uniform vec4 material_ambient;
layout(location = 20) uniform vec4 material_diffuse;
layout(location = 21) uniform vec4 material_specular;
layout(location = 22) uniform vec4 material_emission;
layout(location = 23) uniform float material_shininess;

void main()
{
	// Shadow pass does not require colour
	depth = location.z;
}