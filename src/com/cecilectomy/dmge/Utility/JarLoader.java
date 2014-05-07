package com.cecilectomy.dmge.Utility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * DeadMarsLibrary JarLoader Class
 * 
 * @author Daniel Cecil
 */
public class JarLoader {
    
    /**
     * {@link HashMap} of loaded Jar Classes.
     */
    public static HashMap<String, Class> loadedJars = new HashMap<>();
    
    /**
     * Load a class from a jar.
     * <p>
     * The class will then be available for use.
     * Any class dependencies are loaded as needed by the JVM ClassLoader.
     * 
     * @param jar Filepath to the jar.
     * @param main Name of the class to load.
     * @throws MalformedURLException
     * @throws ClassNotFoundException 
     */
    // TODO: Fix resource leak with ClassLoader.
    public static void loadJar(File jar, String main) throws MalformedURLException, ClassNotFoundException {
        URL[] url = new URL[]{jar.toURI().toURL()};
        ClassLoader cl = new URLClassLoader(url);
        if(!loadedJars.containsKey(jar.getName()))
            loadedJars.put(jar.getName(), cl.loadClass(main));
    }
    
}
