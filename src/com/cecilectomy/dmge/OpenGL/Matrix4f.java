package com.cecilectomy.dmge.OpenGL;

public class Matrix4f {
	
	private float[][] m;
	
	public Matrix4f() {
		this.m = new float[4][4];
	}
	
	public Matrix4f initIdentity() {
		this.m[0][0] = 1; this.m[0][1] = 0; this.m[0][2] = 0; this.m[0][3] = 0;
		this.m[1][0] = 0; this.m[1][1] = 1; this.m[1][2] = 0; this.m[1][3] = 0;
		this.m[2][0] = 0; this.m[2][1] = 0; this.m[2][2] = 1; this.m[2][3] = 0;
		this.m[3][0] = 0; this.m[3][1] = 0; this.m[3][2] = 0; this.m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initTranslation(float x, float y, float z) {
		this.m[0][0] = 1; this.m[0][1] = 0; this.m[0][2] = 0; this.m[0][3] = x;
		this.m[1][0] = 0; this.m[1][1] = 1; this.m[1][2] = 0; this.m[1][3] = y;
		this.m[2][0] = 0; this.m[2][1] = 0; this.m[2][2] = 1; this.m[2][3] = z;
		this.m[3][0] = 0; this.m[3][1] = 0; this.m[3][2] = 0; this.m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initRotation(float x, float y, float z) {
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
		
		float cosX = (float)Math.cos(x);
		float sinX = (float)Math.sin(x);
		float cosY = (float)Math.cos(y);
		float sinY = (float)Math.sin(y);
		float cosZ = (float)Math.cos(z);
		float sinZ = (float)Math.sin(z);
		
		rz.m[0][0] = cosZ;	rz.m[0][1] = -sinZ;	rz.m[0][2] = 0; rz.m[0][3] = 0;
		rz.m[1][0] = sinZ;	rz.m[1][1] =  cosZ;	rz.m[1][2] = 0; rz.m[1][3] = 0;
		rz.m[2][0] = 0;		rz.m[2][1] = 0; 	rz.m[2][2] = 1; rz.m[2][3] = 0;
		rz.m[3][0] = 0;		rz.m[3][1] = 0; 	rz.m[3][2] = 0; rz.m[3][3] = 1;
		
		rx.m[0][0] = 1; rx.m[0][1] = 0;		rx.m[0][2] = 0;		rx.m[0][3] = 0;
		rx.m[1][0] = 0; rx.m[1][1] = cosX;	rx.m[1][2] = -sinX;	rx.m[1][3] = 0;
		rx.m[2][0] = 0; rx.m[2][1] = sinX;	rx.m[2][2] =  cosX;	rx.m[2][3] = 0;
		rx.m[3][0] = 0; rx.m[3][1] = 0;		rx.m[3][2] = 0;		rx.m[3][3] = 1;
		
		ry.m[0][0] = cosY;	ry.m[0][1] = 0; ry.m[0][2] = -sinY;	ry.m[0][3] = 0;
		ry.m[1][0] = 0;		ry.m[1][1] = 1; ry.m[1][2] = 0;		ry.m[1][3] = 0;
		ry.m[2][0] = sinY;	ry.m[2][1] = 0; ry.m[2][2] =  cosY;	ry.m[2][3] = 0;
		ry.m[3][0] = 0;		ry.m[3][1] = 0; ry.m[3][2] = 0;		ry.m[3][3] = 1;
		
		m = rz.mul(ry.mul(rx)).getM();
		
		return this;
	}
	
	public Matrix4f initScale(float x, float y, float z) {
		this.m[0][0] = x; this.m[0][1] = 0; this.m[0][2] = 0; this.m[0][3] = 0;
		this.m[1][0] = 0; this.m[1][1] = y; this.m[1][2] = 0; this.m[1][3] = 0;
		this.m[2][0] = 0; this.m[2][1] = 0; this.m[2][2] = z; this.m[2][3] = 0;
		this.m[3][0] = 0; this.m[3][1] = 0; this.m[3][2] = 0; this.m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f mul(Matrix4f mx) {
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				res.setMAt(i, j, this.m[i][0] * mx.getMAt(0, j) +
								 this.m[i][1] * mx.getMAt(1, j) +
								 this.m[i][2] * mx.getMAt(2, j) +
								 this.m[i][3] * mx.getMAt(3, j)	);
			}
		}
		
		return res;
	}

	public final float[][] getM() {
		final float[][] t = new float[4][4];
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				t[i][j] = this.m[i][j];
			}
		}
		
		return t;
	}
	
	public float getMAt(int x, int y) {
		return this.m[x][y];
	}

	public void setM(float[][] m) {
		this.m = m;
	}
	
	public void setMAt(int x, int y, float v) {
		this.m[x][y] = v;
	}

}
