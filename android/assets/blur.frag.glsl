#ifdef GL_ES
#define LOWP lowp
#define MED mediump
#define HIGH highp
precision mediump float;
#else
#define MED
#define LOWP
#define HIGH
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_resolution;

uniform sampler2D u_texture;

//
vec2 barrelDistortion(vec2 coord, float amt) {
	vec2 cc = coord - 0.5;
	float dist = dot(cc, cc);
	//return coord + cc * (dist*dist)  * amt;
	return coord + cc * dist * amt;

    }
    

void main(){

        vec2 uv = v_texCoords;

    	vec4 a1=texture2D(u_texture, barrelDistortion(uv,0.0));
    	vec4 a2=texture2D(u_texture, barrelDistortion(uv,0.2));
    	vec4 a3=texture2D(u_texture, barrelDistortion(uv,0.4));
    	vec4 a4=texture2D(u_texture, barrelDistortion(uv,0.6));
    	
    	vec4 a5=texture2D(u_texture, barrelDistortion(uv,0.8));
    	vec4 a6=texture2D(u_texture, barrelDistortion(uv,1.0));
    	vec4 a7=texture2D(u_texture, barrelDistortion(uv,1.2));
    	vec4 a8=texture2D(u_texture, barrelDistortion(uv,1.4));
    	
    	vec4 a9=texture2D(u_texture, barrelDistortion(uv,1.6));
    	vec4 a10=texture2D(u_texture, barrelDistortion(uv,1.8));
    	vec4 a11=texture2D(u_texture, barrelDistortion(uv,2.0));
    	vec4 a12=texture2D(u_texture, barrelDistortion(uv,2.2));
    
    	vec4 tx=(a1+a2+a3+a4+a5+a6+a7+a8+a9+a10+a11+a12)/12.;


	gl_FragColor = vec4(tx.rgb,1.);

}


