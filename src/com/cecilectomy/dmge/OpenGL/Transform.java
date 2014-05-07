package com.cecilectomy.dmge.OpenGL;

import com.cecilectomy.dmge.OpenGL.Math.Matrix4f;
import com.cecilectomy.dmge.OpenGL.Math.Vector3f;

public class Transform {
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform() {
		this.translation = new Vector3f(0, 0, 0);
		this.rotation = new Vector3f(0, 0, 0);
		this.scale = new Vector3f(1, 1, 1);
	}
	
	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(
													this.translation.getX(),
													this.translation.getY(),
													this.translation.getZ());
		
		Matrix4f rotationMatrix = new Matrix4f().initRotation(
													this.rotation.getX(),
													this.rotation.getY(),
													this.rotation.getZ());
		
		Matrix4f scaleMatrix = new Matrix4f().initScale(
													this.scale.getX(),
													this.scale.getY(),
													this.scale.getZ());
		
		return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation = new Vector3f(x, y, z);
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

}
