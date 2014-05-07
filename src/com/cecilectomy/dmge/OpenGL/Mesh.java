package com.cecilectomy.dmge.OpenGL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {
	
	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh() {
		this.vbo = glGenBuffers();
		this.ibo = glGenBuffers();
		this.size = 0;
	}
	
	public void addVertices(Vertex[] verts, int[] inds) {
		this.size = verts.length * Vertex.SIZE;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(verts), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(inds), GL_STATIC_DRAW);
		
	}
	
	public void draw() {
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
	}

}
