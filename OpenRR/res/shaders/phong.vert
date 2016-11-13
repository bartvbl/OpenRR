#version 450

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 texCoord;
layout(location = 2) in vec4 normal;

layout(location = 0) out vec4 out_position;
layout(location = 1) out vec2 out_texCoord;
layout(location = 2) out vec4 out_normal;

layout(location = 5) uniform mat4 MVP;
layout(location = 6) uniform mat4 MVP_normal;

void main( void )
{
	out_position = MVP * position;
	out_texCoord = texCoord;
	out_normal = MVP_normal * normal;

	gl_Position = out_position;
}