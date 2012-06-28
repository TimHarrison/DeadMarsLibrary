package deadmarslib.Utility;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// </editor-fold>

/**
 * DeadMarsLib SecurityUtility Class
 * 
 * @author Daniel Cecil
 */
public class SecurityUtility {
    
    /**
     * Create an MD5 hash of a string.
     * 
     * @param md5 String to hash.
     * @return String representation of hash.
     */
    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        return null;
    }
    
}
