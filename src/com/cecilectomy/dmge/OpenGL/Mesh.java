package com.cecilectomy.dmge.OpenGL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.cecilectomy.dmge.Math.Vector3f;

public class Mesh {
	
	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh() {
		this.vbo = glGenBuffers();
		this.ibo = glGenBuffers();
		this.size = 0;
	}
	
	public static Mesh load(String fileName) {
		String[] pathSegments = fileName.split("\\.");
		String ext = pathSegments[pathSegments.length - 1];
		
		if(!ext.equals("obj")) {
			System.err.printf("File format %s not supported.\n", ext);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		
		ArrayList<Vertex> vertices = new ArrayList<>();
		ArrayList<Integer> indices = new ArrayList<>();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("./res/models/"+fileName));
			String line;
			while((line = br.readLine()) != null) {
				String[] lineTokens = line.split(" ");
				lineTokens = Util.cleanTokens(lineTokens);
				
				if(lineTokens.length == 0 || lineTokens[0].equals("#")) {
					continue;
				} else if(lineTokens[0].equals("v")) {
					vertices.add(
							new Vertex(
									new Vector3f(
											Float.valueOf(lineTokens[1]),
											Float.valueOf(lineTokens[2]),
											Float.valueOf(lineTokens[3])
									)
							)
					);
				} else if(lineTokens[0].equals("f")) {
					indices.add(Integer.parseInt(lineTokens[1]) - 1);
					indices.add(Integer.parseInt(lineTokens[2]) - 1);
					indices.add(Integer.parseInt(lineTokens[3]) - 1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Mesh mesh = new Mesh();
		Vertex[] vData = new Vertex[vertices.size()];
		vertices.toArray(vData);
		
		Integer[] iData = new Integer[indices.size()];
		indices.toArray(iData);
		
		mesh.addVertices(vData, Util.toIntArray(iData));
		
		return mesh;
	}
	
	public void addVertices(Vertex[] vertices, int[] indices) {
		this.size = indices.length;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
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
