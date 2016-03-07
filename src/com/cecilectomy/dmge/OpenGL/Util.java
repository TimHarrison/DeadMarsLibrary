package com.cecilectomy.dmge.OpenGL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import com.cecilectomy.dmge.Math.Matrix4f;

public class Util {
	
	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values) {
		IntBuffer buffer = Util.createIntBuffer(values.length);
		
		buffer.put(values);
		buffer.flip();
		
		return buffer;

	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] verts) {
		FloatBuffer buffer = Util.createFloatBuffer(verts.length * Vertex.SIZE);
		
		for(int x = 0; x < verts.length; x++) {
			buffer.put(verts[x].getPos().getX());
			buffer.put(verts[x].getPos().getY());
			buffer.put(verts[x].getPos().getZ());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f m) {
		FloatBuffer buffer = Util.createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				buffer.put(m.getMAt(i, j));
		
		buffer.flip();
		return buffer;
	}
	
	public static String[] cleanTokens(String[] tokens) {
		ArrayList<String> tokenList = new ArrayList<>();
		
		for(int i = 0; i < tokens.length; i++) {
			if(!tokens[i].equals(" ")) {
				tokenList.add(tokens[i]);
			}
		}
		
		String[] res = new String[tokenList.size()];
		tokenList.toArray(res);
		
		return res;
	}
	
	public static int[] toIntArray(Integer[] data) {
		int[] intArray = new int[data.length];
		
		for(int i = 0; i < data.length; i++) {
			intArray[i] = data[i];
		}
		
		return intArray;
	}

}
