#version 450

in layout(location=0) vec4 position;
in layout(location=1) vec2 textureCoordinate;
in layout(location=2) vec4 normal;

out layout(location=4) vec4 outPosition;
out layout(location=5) vec2 outTexCoord;
out layout(location=6) vec4 outNormal;

uniform layout(location=16) mat4 MVPMatrix;
uniform layout(location=20) mat4 MVPNormalMatrix;

void main( void )
{
	vec4 newPosition = MVPMatrix * position;
	
	gl_Position = newPosition;
	outPosition = newPosition;

	outNormal = MVPNormalMatrix * normal;
	
	outTexCoord = textureCoordinate;
}