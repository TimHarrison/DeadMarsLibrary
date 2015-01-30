package com.cecilectomy.dmge.Assets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * DeadMarsLibrary AssetManager Class
 * 
 * @author Daniel Cecil
 */
public class AssetManager {

	protected static HashMap<String, Object> assets = new HashMap<>();
	protected static String baseAssetPath = "";

	/**
	 * Get the base asset path.
	 * 
	 * @return
	 */
	public static String getBaseAssetPath() {
		return baseAssetPath;
	}

	/**
	 * Set the base asset path.
	 * 
	 * @param baseAssetPath
	 */
	public static void setBaseAssetPath(String baseAssetPath) {
		AssetManager.baseAssetPath = baseAssetPath;
	}

	/**
	 * Retrieve the asset with given name.
	 * 
	 * @param name Name of asset to retrieve
	 * @return Asset object. Must be cast to correct type.
	 */
	public static Object getAsset(String name) {
		if (assets.containsKey(name)) {
			return assets.get(name);
		}
		return null;
	}

	/**
	 * Remove the asset with given name.
	 * 
	 * @param name Name of asset to remove.
	 * @return Removed asset object.
	 */
	public static Object removeAsset(String name) {
		return assets.remove(name);
	}

	/**
	 * Add an asset with given name and asset object.
	 * 
	 * @param name Name of asset to add.
	 * @param asset Object of asset to add.
	 * @return Returns true if asset added successfully. Returns false if asset already exists.
	 */
	public static boolean addAsset(String name, Object asset) {
		if(assets.containsKey(name)) {
			return false;
		}

		assets.put(name, asset);
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
	public static <T> boolean addAsset(Class<T> type, String name, String path) throws IOException {
		if(assets.containsKey(name)) {
			return false;
		}

	    assets.put(name, AssetManager.loadAsset(type, path));
		
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
	public static <T> Object loadAsset(Class<T> type, String path) throws IOException {
		if(type.equals(BufferedImage.class)) {
	        BufferedImage img = ImageIO.read(AssetManager.class.getResource(baseAssetPath+path));
	        return img;
		} else if(type.equals(AudioClip.class)) {
	        AudioClip sound = Applet.newAudioClip(AssetHelper.class.getResource(baseAssetPath+path));
	        return sound;
		} else if(type.equals(String.class)) {
			File file = new File(AssetManager.class.getResource(baseAssetPath+path).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}

			br.close();
			
			return sb.toString();
		} else {
			throw new IOException();
		}
	}

}
