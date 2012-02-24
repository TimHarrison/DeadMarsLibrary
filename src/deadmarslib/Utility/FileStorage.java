package deadmarslib.Utility;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
// </editor-fold>

/**
 * DeadMarsLib FileStorage Class
 * 
 * @author Daniel Cecil
 */
public class FileStorage {
    
    /**
     * Saves an object to a serialized file.
     * 
     * @param filename Name to save file as.
     * @param obj Object to serialize to file.
     */
    public static void Save(String filename, Object obj) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(obj);
            out.close();
            fos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Loads a serialized object file into an Object.
     * <p>
     * You must cast the returned Object to the same Object as when it was saved.
     * 
     * @param filename Name of serialized object file to load.
     * @return Object containing loaded serialized object.
     */
    public static Object Load(String filename) {        
        Object obj = null;
        
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            obj = in.readObject();
            in.close();
            fis.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return obj;
    }
    
}
