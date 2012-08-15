package deadmarslib.Utility;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.*;
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
    public static void save(String filename, Object obj) {
        //Untested FindBugs Bug-fix
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            fos = new FileOutputStream(filename);
            try {
                out = new ObjectOutputStream(fos);
                try {
                    out.writeObject(obj);
                    out.close();
                    fos.close();
                } catch (IOException ex) {
                    out.close();
                    fos.close();
                }
            } catch (IOException ex) {
            }
        } catch (FileNotFoundException ex) {
        }
    }

    /**
     * Loads a serialized object file into an Object. <p> You must cast the
     * returned Object to the same Object as when it was saved.
     *
     * @param filename Name of serialized object file to load.
     * @return Object containing loaded serialized object.
     */
    public static Object load(String filename) {
        //Untested FindBugs Bug-fix
        Object obj = null;

        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(filename);
            try {
                in = new ObjectInputStream(fis);
                try {
                    obj = in.readObject();
                    in.close();
                    fis.close();
                } catch (IOException | ClassNotFoundException ex) {
                    in.close();
                    fis.close();
                }
            } catch (IOException ex) {
            }
        } catch (FileNotFoundException ex) {
        }

        return obj;
    }
}
