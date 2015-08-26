package com.cecilectomy.dmge.Assets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.cecilectomy.dmge.Assets.Loaders.AssetLoader;

/**
 * DeadMarsLibrary AssetManager Class
 * 
 * @author Daniel Cecil
 */
public class AssetManager {

	private HashMap<String, Object> assets = new HashMap<>();
	private String baseAssetPath = "";
	private static AssetManager globalInstance = null;
	private static String AssetLoaderPackage = "com.cecilectomy.dmge.Assets.Loaders";
	
	public AssetManager() {
		this("");
	}
	
	public AssetManager(String assetPath) {
		this.baseAssetPath = assetPath;
	}
	
	/**
	 * Get global {@link AssetManager} instance.
	 * 
	 * @return Global {@link AssetManager} instance
	 */
	public static AssetManager getInstance() {
		if(globalInstance == null) {
			globalInstance = new AssetManager();
		}
		return globalInstance;
	}

	/**
	 * Get the base asset path.
	 * 
	 * @return baseAssetPath.
	 */
	public String getBaseAssetPath() {
		return baseAssetPath;
	}

	/**
	 * Set the base asset path.
	 * 
	 * @param baseAssetPath.
	 */
	public void setBaseAssetPath(String baseAssetPath) {
		this.baseAssetPath = baseAssetPath;
	}

	/**
	 * Retrieve the asset with given name.
	 * 
	 * @param name Name of asset to retrieve
	 * @return Asset object. Must be cast to correct type.
	 */
	public Object getAsset(String name) {
		if (this.assets.containsKey(name)) {
			return this.assets.get(name);
		}
		return null;
	}

	/**
	 * Remove the asset with given name.
	 * 
	 * @param name Name of asset to remove.
	 * @return Removed asset object.
	 */
	public Object removeAsset(String name) {
		return this.assets.remove(name);
	}

	/**
	 * Add an asset with given name and asset object.
	 * 
	 * @param name Name of asset to add.
	 * @param asset Object of asset to add.
	 * @return Returns true if asset added successfully. Returns false if asset already exists.
	 */
	public boolean addAsset(String name, Object asset) {
		if(this.assets.containsKey(name)) {
			return false;
		}

		this.assets.put(name, asset);
		return true;
	}
	
	/**
	 * Load and add an asset with given type, name, and asset path.
	 * 
	 * @param type type of asset being loaded/added.
	 * @param name Name of asset to load/add.
	 * @param asset Path of asset to load/add.
	 * @return Returns true if asset loaded/added successfully. Returns false if asset already exists.
	 * @throws IOException
	 */
	public <T> boolean addAsset(Class<T> type, String name, String path) throws IOException {
		if(this.assets.containsKey(name)) {
			return false;
		}

		this.assets.put(name, this.loadAsset(type, path));
		
		return true;
	}

	/**
	 * Loads an asset from path.
	 * 
	 * @param type Type of asset being loaded.
	 * @param asset Path of asset to load.
	 * @return Returns true if asset successfully loaded. Returns false if asset could not be loaded.
	 * @throws IOException Asset not found or error loading asset.
	 */
	public <T> Object loadAsset(Class<T> type, String path) throws IOException {

		try {
			Class<?> clazz = Class.forName(AssetLoaderPackage + "." + type.getSimpleName() + "AssetLoader");
			Constructor<?> ctor = clazz.getConstructor();
			AssetLoader obj = (AssetLoader) ctor.newInstance();
			Object asset = obj.load(this.baseAssetPath + path);
			return asset;
		} catch (Exception e) {
			throw new IOException("Unable to load " + this.baseAssetPath + path);
		}
	}

}
