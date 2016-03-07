package com.cecilectomy.dmge.Rendering;

import java.awt.Dimension;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

import com.cecilectomy.dmge.Rendering.Details.DetailRenderer;

public abstract class Renderer {

	protected String name = "";
	private HashMap<String, DetailRenderer> detailRenderers = new HashMap<String, DetailRenderer>();
	
	public Dimension getResolution(){
		return null;
	}
	
	public void setResolution(Dimension res){
		
	}
	
	public void setResolution(int width, int height){
		
	}
	
	public void setResolutionChanged(boolean flag){
		
	}
	
	public boolean resolutionChanged(){
		return false;
	}
	
	public String getRendererName() {
		return this.name;
	}
	
	public void clear(){
		
	}
	
	public final void render(List<RenderDetails> renderDetails){
		for(RenderDetails detail : renderDetails) {
			try {
				String type = (String)detail.details.get("type");
				String detailRendererName = "com.cecilectomy.dmge.Rendering.Details." + this.getRendererName() + type + "DetailRenderer";
				if(!detailRenderers.containsKey(detailRendererName)) {
					Class<?> clazz = Class.forName(detailRendererName);
					Constructor<?> ctor = clazz.getConstructor();
					DetailRenderer ren = (DetailRenderer) ctor.newInstance();
					detailRenderers.put(detailRendererName, ren);
				}
				
				detailRenderers.get(detailRendererName).render(this, detail);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(){
		
	}
	
	public void initialize(){
		
	}
	
	public void cleanUp(){
		
	}
	
}
