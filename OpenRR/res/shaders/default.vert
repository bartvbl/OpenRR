#version 450

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 texCoord;

layout(location = 0) out vec4 out_position;
layout(location = 1) out vec2 out_texCoord;

layout(location = 6) uniform mat4 MVP;
layout(location = 10) uniform mat4 MVP_normal;

void main( void )
{
	out_position = MVP * position;
	out_texCoord = texCoord;

	gl_Position = out_position;
}