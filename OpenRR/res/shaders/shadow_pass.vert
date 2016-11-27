#version 450

in layout(location=0) vec4 position;
in layout(location=1) vec2 textureCoordinate;

out layout(location = 0) vec4 outPosition;

uniform layout(location=2) mat4 MVMatrix;
uniform layout(location=6) mat4 MVPMatrix;
uniform layout(location=10) mat4 MVPNormalMatrix;
uniform layout(location = 35) mat4 lightMVP;

void main( void )
{
	vec4 newPosition = MVPMatrix * position;
	outPosition = newPosition;
	gl_Position = newPosition;
}