package com.cecilectomy.dmge.Utility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class JarLoader {
	
    public static HashMap<String, Class<?>> loadedJars = new HashMap<>();
    
	public static void loadJar(File jar, String main) throws MalformedURLException, ClassNotFoundException {
        URL[] url = new URL[]{jar.toURI().toURL()};
        
        // We cannot close the class loader since we are loading jars
        // as extension code possibly for the full life cycle of the
        // application. Must be closed manually by the user, or at end
        // of application life cycle.
        @SuppressWarnings("resource")
		ClassLoader cl = new URLClassLoader(url);
        
        if(!loadedJars.containsKey(jar.getName())) {
            loadedJars.put(jar.getName(), cl.loadClass(main));
        }
    }
    
}
