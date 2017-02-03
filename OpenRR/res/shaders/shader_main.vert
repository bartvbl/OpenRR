#version 450

{% flavour default (textures) %}
{% flavour shadow_phong (textures, lighting, shadow) %}
{% flavour shadow_pass (shadowDepthPass) %}
{% flavour boneAnimation (textures, lighting, boneAnimation) %}
{% flavour colourMaterial (lighting) %}

{% requires shadow lighting %}

	in layout(location = 0) vec4 position;

{% if textures %}
	in layout(location = 1) vec2 textureCoordinate;
{% endif %}

{% if lighting %}
	in layout(location = 2) vec4 normal;
{% endif %}

	out layout(location = 0) vec4 outPosition;

{% if textures %}
	out layout(location = 1) vec2 outTexCoord;
{% endif %}

{% if lighting %}
	out layout(location = 2) vec4 outNormal;
	out layout(location = 3) vec4 outLightPosition;
{% endif %}

	uniform layout(location = 2) mat4 pieceMMatrix;
	uniform layout(location = 6) mat4 pieceMVPMatrix;
	uniform layout(location = 10) mat4 pieceMVPNormalMatrix;

{% if shadow %}
	uniform layout(location = 35) mat4 lightMVP;
{% endif %}

{% if boneAnimation %}
	uniform layout(location = 50) mat4 boneMMatrix;
	uniform layout(location = 54) mat4 boneMVPMatrix;
	uniform layout(location = 58) mat4 boneMVPNormalMatrix;

	uniform layout(location = 62) vec3 boneVector;
	uniform layout(location = 63) vec3 boneOrigin;
	uniform layout(location = 64) mat4 boneMV;
	uniform layout(location = 68) mat4 boneMVP;
{% endif %}

	void main( void )
	{
		vec4 partPosition = pieceMVPMatrix * position;
{% if boneAnimation %}
		vec4 bonePosition = boneMV * position;
{% endif %}

		outPosition = pieceMMatrix * position;
		gl_Position = outPosition;
		
{% if lighting %}
		outLightPosition = lightMVP * position;
		outNormal = pieceMVPNormalMatrix * normal;
{% endif %}

{% if textures %}
		outTexCoord = textureCoordinate;
{% endif %}
	}