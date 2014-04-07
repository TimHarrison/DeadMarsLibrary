package deadmarslib.Assets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
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
	        BufferedImage img = ImageIO.read(AssetManager.class.getResource(path));
	        return img;
		} else if(type.equals(AudioClip.class)) {
	        AudioClip sound = Applet.newAudioClip(AssetHelper.class.getResource(path));
	        return sound;
		} else {
			throw new IOException();
		}
	}

}
