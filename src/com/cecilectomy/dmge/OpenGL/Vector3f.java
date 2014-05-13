package com.cecilectomy.dmge.OpenGL;

public class Vector3f extends Vector2f{
	
	protected float z;
	
	public Vector3f(float x, float y, float z) {
		super(x, y);
		this.setZ(z);
	}
	
	@Override
	public float length() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public float dot(Vector3f v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	
	public Vector3f cross(Vector3f v) {
		float _x = y * v.z - z * v.y;
		float _y = z * v.x - x * v.z;
		float _z = x * v.y - y * v.x;
		
		return new Vector3f(_x, _y, _z);
	}
	
	public Vector3f normalize() {
		float length = this.length();
		
		x /= length;
		y /= length;
		z /= length;
		
		return this;
	}
	
	public Vector3f rotate() {
		return null;
	}
	
	public Vector3f add(Vector3f v) {
		return new Vector3f(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	public Vector3f add(float v) {
		return new Vector3f(this.x + v, this.y + v, this.z + v);
	}
	
	public Vector3f sub(Vector3f v) {
		return new Vector3f(this.x - v.x, this.y - v.y, this.z - v.z);
	}
	
	public Vector3f sub(float v) {
		return new Vector3f(this.x - v, this.y - v, this.z - v);
	}
	
	public Vector3f mul(Vector3f v) {
		return new Vector3f(this.x * v.x, this.y * v.y, this.z * v.z);
	}
	
	public Vector3f mul(float v) {
		return new Vector3f(this.x * v, this.y * v, this.z * v);
	}
	
	public Vector3f div(Vector3f v) {
		return new Vector3f(this.x / v.x, this.y / v.y, this.z / v.z);
	}
	
	public Vector3f div(float v) {
		return new Vector3f(this.x / v, this.y / v, this.z / v);
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
