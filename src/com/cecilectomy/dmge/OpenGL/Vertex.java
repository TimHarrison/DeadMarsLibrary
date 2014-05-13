package com.cecilectomy.dmge.OpenGL;



public class Vertex {
	
	public static final int SIZE = 3;
	
	private Vector3f pos;
	
	public Vertex(Vector3f pos) {
		this.pos = pos;
	}
	
	public Vector3f getPos() {
		return this.pos;
	}
	
	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

}
