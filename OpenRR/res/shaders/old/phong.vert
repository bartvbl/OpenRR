#version 450

in layout(location=0) vec4 position;
in layout(location=1) vec2 textureCoordinate;
in layout(location=2) vec4 normal;

out layout(location=0) vec4 outPosition;
out layout(location=1) vec2 outTexCoord;
out layout(location=2) vec4 outNormal;

uniform layout(location=2) mat4 MMatrix;
uniform layout(location=6) mat4 MVPMatrix;
uniform layout(location=10) mat4 MVPNormalMatrix;

void main( void )
{
	vec4 newPosition = MVPMatrix * position;
	
	gl_Position = newPosition;
	outPosition = MMatrix * position;

	outNormal = MVPNormalMatrix * normal;
	
	outTexCoord = textureCoordinate;
}