package deadmarslib.Utility;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
//</editor-fold>

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
	 * Loads an asset from path.
	 * 
	 * @param type Type of asset being loaded.
	 * @param name Name to store asset with.
	 * @param asset Path of asset to load.
	 * @return Returns true if asset successfully loaded. Returns false if asset could not be loaded.
	 * @throws IOException Asset not found.
	 */
	public static <T> boolean loadAsset(Class<T> type, String name, String asset) throws IOException {
		if(assets.containsKey(name)) {
			return false;
		}
		
		if(type.equals(BufferedImage.class)) {
	        BufferedImage img = ImageIO.read(AssetManager.class.getResource(asset));
	        assets.put(name, img);
		} else if(type.equals(AudioClip.class)) {
	        AudioClip sound = Applet.newAudioClip(ContentManager.class.getResource(asset));
	        assets.put(name, sound);
		} else {
			throw new IOException();
		}
		
		return true;
	}

}
