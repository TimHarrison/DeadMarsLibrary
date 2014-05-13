package com.cecilectomy.dmge.OpenGL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

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

}
