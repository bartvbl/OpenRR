#version 120

varying vec3 normal;
varying vec3 position;

uniform sampler2D diffuseTexture;
uniform float texturesEnabled;

vec4 lightSource(vec3 norm, vec3 view, gl_LightSourceParameters light)
{
	vec3 lightVector = normalize(light.position.xyz - view);
	vec3 reflection = normalize(lightVector - normalize(view.xyz));

	float diffuseFactor = max(0.0, dot(norm, lightVector));
	float specularDot = max(0.0, dot(norm, reflection));

	float specularFactor = pow(specularDot, gl_FrontMaterial.shininess);
	
	vec4 ambient = vec4(vec3(gl_FrontMaterial.ambient.rgb * light.ambient.rgb), 0.0);
	vec4 diffuse = vec4(vec3(gl_FrontMaterial.diffuse.rgb * light.diffuse.rgb * diffuseFactor), gl_FrontMaterial.diffuse.a);
	vec4 specular = vec4(vec3(gl_FrontMaterial.specular.rgb * light.specular.rgb * specularFactor), 0.0);
	
	return ambient + diffuse + specular;
}

vec4 lighting()
{
	// normal might be damaged by linear interpolation.
	vec3 norm = normalize(normal);

	return
		vec4(gl_FrontMaterial.emission.rgb, 0.0) +
		lightSource(norm, position, gl_LightSource[0]);
}

void main()
{
	vec4 lightValue = lighting();
	gl_FragColor = (texturesEnabled * texture2D(diffuseTexture, gl_TexCoord[0].st) * lightValue) + ((1.0 - texturesEnabled) * lightValue) * vec4(0, 1, 1, 1);
}