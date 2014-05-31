package com.cecilectomy.dmge.Math;

public class Vector3f {
	
	protected float x, y, z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f add(Vector3f vec) {
		return new Vector3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}
	
	public Vector3f add(float scalar) {
		return new Vector3f(this.x + scalar, this.y + scalar, this.z + scalar);
	}
	
	public Vector3f sub(Vector3f vec) {
		return new Vector3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
	}
	
	public Vector3f sub(float scalar) {
		return new Vector3f(this.x - scalar, this.y - scalar, this.z - scalar);
	}
	
	public Vector3f mul(Vector3f vec) {
		return new Vector3f(this.x * vec.x, this.y * vec.y, this.z * vec.z);
	}
	
	public Vector3f mul(float scalar) {
		return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
	}
	
	public Vector3f div(Vector3f vec) {
		return new Vector3f(this.x / vec.x, this.y / vec.y, this.z / vec.z);
	}
	
	public Vector3f div(float scalar) {
		return new Vector3f(this.x / scalar, this.y / scalar, this.z / scalar);
	}
	
	public float dot(Vector3f v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	
	public Vector3f cross(Vector3f v) {
		float _x = this.y * v.z - this.z * v.y;
		float _y = this.z * v.x - this.x * v.z;
		float _z = this.x * v.y - this.y * v.x;
		
		return new Vector3f(_x, _y, _z);
	}

	public float length() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	
	public float lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	public Vector3f unit() {
		return this.div(this.length());
	}
	
	public Vector3f normalized() {
		return this.unit();
	}
	
	public Vector3f rotate() {
		return null;
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(Vector3f vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return this.z;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
