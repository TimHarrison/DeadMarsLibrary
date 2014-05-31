package com.cecilectomy.dmge.Math;

public class Vector2f {
	
	protected float x, y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f add(Vector2f vec) {
		return new Vector2f(this.x + vec.x, this.y + vec.y);
	}
	
	public Vector2f add(float scalar) {
		return new Vector2f(this.x + scalar, this.y + scalar);
	}
	
	public Vector2f sub(Vector2f vec) {
		return new Vector2f(this.x - vec.x, this.y - vec.y);
	}
	
	public Vector2f sub(float scalar) {
		return new Vector2f(this.x - scalar, this.y + scalar);
	}
	
	public Vector2f mul(Vector2f vec) {
		return new Vector2f(this.x * vec.x, this.y * vec.y);
	}
	
	public Vector2f mul(float scalar) {
		return new Vector2f(this.x * scalar, this.y * scalar);
	}
	
	public Vector2f div(Vector2f vec) {
		return new Vector2f(this.x / vec.x, this.y / vec.y);
	}
	
	public Vector2f div(float scalar) {
		return new Vector2f(this.x / scalar, this.y / scalar);
	}
	
	public float dot(Vector2f vec) {
		return this.x * vec.x + this.y * vec.y;
	}
	
	public float cross(Vector2f vec) {
		return this.x * vec.y - this.y * vec.x;
	}
	
	public float length() {
		return (float)Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public float lengthSquared() {
		return this.x * this.x + this.y * this.y;
	}
	
	public Vector2f unit() {
		return this.div(this.length());
	}
	
	public Vector2f normalized() {
		return this.unit();
	}
	
	public Vector2f rotate(float angle) {
		double rad = Math.toRadians(angle);
		double sin = Math.sin(rad);
		double cos = Math.cos(rad);
		
		return new Vector2f((float)(this.x * cos - this.y * sin), (float)(this.x * sin - this.y * cos));
	}
	
	public float angle() {
		return (float)Math.atan2(this.y, this.x);
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2f vec) {
		this.x = vec.x;
		this.y = vec.y;
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

}
