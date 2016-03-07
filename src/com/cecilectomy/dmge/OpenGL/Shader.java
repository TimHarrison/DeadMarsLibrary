package com.cecilectomy.dmge.OpenGL;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.cecilectomy.dmge.Math.Matrix4f;
import com.cecilectomy.dmge.Math.Vector3f;

public class Shader {

	private int program;
	private HashMap<String, Integer> uniforms;
	
	public Shader() {
		this.uniforms = new HashMap<String, Integer>();
		this.program = glCreateProgram();
		
		if(this.program == 0) {
			System.out.println("Could not construct program memory location.");
			System.exit(1);
		}
	}
	
	public static String load(String fileName) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("./res/shaders/"+fileName));
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

	public void bind() {
		glUseProgram(this.program);
	}
	
	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	// TODO (Daniel): Look back in to OpenGL programming to address the deprecation here.
	public void compileProgram() {
		glLinkProgram(this.program);
		
		if(glGetProgram(this.program, GL_LINK_STATUS) == 0) {
			System.out.println("Could not link program.");
			System.out.println(glGetProgramInfoLog(this.program, 1024));
			System.exit(1);
		}
		
		glValidateProgram(this.program);
		
		if(glGetProgram(this.program, GL_VALIDATE_STATUS) == 0) {
			System.out.println("Could not validate program.");
			System.out.println(glGetProgramInfoLog(this.program, 1024));
			System.exit(1);
		}
	}

	// TODO (Daniel): Look back in to OpenGL programming to address the deprecation here.
	private void addProgram(String text, int type) {
		int shader = glCreateShader(type);
		
		if(shader == 0) {
			System.out.println("Could not create shader memory location.");
			System.out.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShader(shader, GL_COMPILE_STATUS) == 0) {
			System.out.println("Could not compile shader.");
			System.out.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	public void addUniform(String uniformName) {
		int uniformLocation = glGetUniformLocation(this.program, uniformName);
		
		if(uniformLocation == 0xFFFFFFFF) {
			System.out.println("Could not get uniform memory location.");
			System.exit(1);
		}
		
		uniforms.put(uniformName, uniformLocation);
	}
	
	public void setUniformi(String uniform, int value) {
		glUniform1i(uniforms.get(uniform), value);
	}
	
	public void setUniformf(String uniform, float value) {
		glUniform1f(uniforms.get(uniform), value);
	}
	
	public void setUniform(String uniform, Vector3f value) {
		glUniform3f(uniforms.get(uniform), value.getX(), value.getY(), value.getZ());
	}
	
	public void setUniform(String uniform, Matrix4f value) {
		glUniformMatrix4(uniforms.get(uniform), true, Util.createFlippedBuffer(value));
	}

}
