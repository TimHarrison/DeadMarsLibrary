package deadmarslib.Utility;

import java.security.MessageDigest;

/**
 * DeadMarsLib SecurityUtility Class
 * 
 * @author Daniel Cecil
 */
public class SecurityUtility {
    
    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }
    
}
