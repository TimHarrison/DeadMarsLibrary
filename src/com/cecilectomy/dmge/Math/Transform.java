package com.cecilectomy.dmge.Math;



public class Transform {
	
	private static float zNear;
	private static float zFar;
	private static float width;
	private static float height;
	private static float fov;
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform() {
		this.translation = new Vector3f(0, 0, 0);
		this.rotation = new Vector3f(0, 0, 0);
		this.scale = new Vector3f(1, 1, 1);
	}
	
	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().setTranslation(
													this.translation.getX(),
													this.translation.getY(),
													this.translation.getZ());
		
		Matrix4f rotationMatrix = new Matrix4f().setRotation(
													this.rotation.getX(),
													this.rotation.getY(),
													this.rotation.getZ());
		
		Matrix4f scaleMatrix = new Matrix4f().setScale(
													this.scale.getX(),
													this.scale.getY(),
													this.scale.getZ());
		
		return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
	}
	
	public Matrix4f getProjectedTransformation() {
		Matrix4f projectionMatrix = new Matrix4f().setProjection(fov, width, height, zNear, zFar);
		
		return projectionMatrix.mul(getTransformation());
	}
	
	public static void setProjection(float fov, float width, float height, float zNear, float zFar) {
		Transform.fov = fov;
		Transform.width = width;
		Transform.height = height;
		Transform.zNear = zNear;
		Transform.zFar = zFar;
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public Transform setTranslation(Vector3f translation) {
		this.translation = translation;
		
		return this;
	}
	
	public Transform setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
		
		return this;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Transform setRotation(Vector3f rotation) {
		this.rotation = rotation;
		
		return this;
	}
	
	public Transform setRotation(float x, float y, float z) {
		this.rotation = new Vector3f(x, y, z);
		
		return this;
	}

	public Vector3f getScale() {
		return scale;
	}

	public Transform setScale(Vector3f scale) {
		this.scale = scale;
		
		return this;
	}
	
	public Transform setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
		
		return this;
	}

}
