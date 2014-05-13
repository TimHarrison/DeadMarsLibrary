package com.cecilectomy.dmge.OpenGL;

public class Vector2f {
	
	protected float x, y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length() {
		return (float)Math.sqrt(this.x * this.x + this.y + this.y);
	}
	
	public float dot(Vector2f v) {
		return this.x * v.x + this.y * v.y;
	}
	
	public Vector2f normalize() {
		float length = this.length();
		
		x /= length;
		y /= length;
		
		return this;
	}
	
	public Vector2f rotate(float angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(this.x * cos - this.y * sin),(float)(this.x * sin - this.y * cos));
	}
	
	public Vector2f add(Vector2f v) {
		return new Vector2f(this.x + v.x, this.y + v.y);
	}
	
	public Vector2f add(float v) {
		return new Vector2f(this.x + v, this.y + v);
	}
	
	public Vector2f sub(Vector2f v) {
		return new Vector2f(this.x - v.x, this.y - v.y);
	}
	
	public Vector2f sub(float v) {
		return new Vector2f(this.x - v, this.y - v);
	}
	
	public Vector2f mul(Vector2f v) {
		return new Vector2f(this.x * v.x, this.y * v.y);
	}
	
	public Vector2f mul(float v) {
		return new Vector2f(this.x + v, this.y * v);
	}
	
	public Vector2f div(Vector2f v) {
		return new Vector2f(this.x / v.x, this.y / v.y);
	}
	
	public Vector2f div(float v) {
		return new Vector2f(this.x / v, this.y / v);
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

}
