package com.cecilectomy.dmge.Assets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.cecilectomy.dmge.Assets.Loaders.AssetLoader;

public class AssetManager {

	private HashMap<String, Object> assets = new HashMap<>();
	private HashMap<String, AssetLoader> assetLoaders = new HashMap<String, AssetLoader>();
	private String baseAssetPath = "";
	private static AssetManager globalInstance = null;
	private static String AssetLoaderPackage = "com.cecilectomy.dmge.Assets.Loaders";
	
	public AssetManager() {
		this("");
	}
	
	public AssetManager(String assetPath) {
		this.baseAssetPath = assetPath;
	}
	
	public static AssetManager getInstance() {
		if(globalInstance == null) {
			globalInstance = new AssetManager();
		}
		return globalInstance;
	}

	public String getBaseAssetPath() {
		return baseAssetPath;
	}
	
	public void setBaseAssetPath(String baseAssetPath) {
		this.baseAssetPath = baseAssetPath;
	}
	
	public Object getAsset(String name) {
		if (this.assets.containsKey(name)) {
			return this.assets.get(name);
		}
		return null;
	}
	
	public Object removeAsset(String name) {
		return this.assets.remove(name);
	}
	
	public boolean addAsset(String name, Object asset) {
		if(this.assets.containsKey(name)) {
			return false;
		}

		this.assets.put(name, asset);
		return true;
	}
	
	public <T> boolean addAsset(Class<T> type, String name, String path) throws IOException {
		if(this.assets.containsKey(name)) {
			return false;
		}

		this.assets.put(name, this.loadAsset(type, path));
		
		return true;
	}
	
	public <T> Object loadAsset(Class<T> type, String path) throws IOException {

		try {
			String assetLoaderName = AssetLoaderPackage + "." + type.getSimpleName() + "AssetLoader";
			if(!assetLoaders.containsKey(assetLoaderName)) {
				Class<?> clazz = Class.forName(assetLoaderName);
				Constructor<?> ctor = clazz.getConstructor();
				AssetLoader obj = (AssetLoader) ctor.newInstance();
				assetLoaders.put(assetLoaderName, obj);
			}
			
			Object asset = assetLoaders.get(assetLoaderName).load(this.baseAssetPath + path);
			return asset;
		} catch (Exception e) {
			throw new IOException("Unable to load " + this.baseAssetPath + path);
		}
	}

}
