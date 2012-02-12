package deadmarslib.Utility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class JarLoader {
    
    public static HashMap<String, Class> loadedJars = new HashMap<String, Class>();
    
    public static void loadJar(File jar, String main) throws MalformedURLException, ClassNotFoundException {
        URL[] url = new URL[]{jar.toURI().toURL()};
        ClassLoader cl = new URLClassLoader(url);
        if(!loadedJars.containsKey(jar.getName()))
            loadedJars.put(jar.getName(), cl.loadClass(main));
    }
    
}
