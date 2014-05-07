package com.cecilectomy.dmge.OpenGL.Math;

public class Quaternion {
	private float x, y, z, w;

	public Quaternion(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public float length() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
	}
	
	public Quaternion normalize() {
		float length = this.length();
		
		x /= length;
		y /= length;
		z /= length;
		w /= length;
		
		return this;
	}
	
	public Quaternion conjugate() {
		return new Quaternion(-this.x, -this.y, -this.z, this.w);
	}
	
	public Quaternion mul(Quaternion q) {
		float _w = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
		float _x = this.x * q.w + this.w * q.x + this.y * q.z - this.z * q.y;
		float _y = this.y * q.w + this.w * q.y + this.z * q.x - this.x * q.z;
		float _z = this.z * q.w + this.w * q.z + this.x * q.y - this.y * q.x;
		
		return new Quaternion(_x, _y, _z, _w);
	}
	
	public Quaternion mul(Vector3f v) {
		float _w = -this.x * v.x - this.y * v.y - this.z * v.z;
		float _x =  this.w * v.x + this.y * v.z - this.z * v.y;
		float _y =  this.w * v.y + this.z * v.x - this.x * v.z;
		float _z =  this.w * v.z + this.x * v.y - this.y * v.x;
		
		return new Quaternion(_x, _y, _z, _w);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
}
