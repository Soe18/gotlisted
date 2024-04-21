package gotlisted;

import java.security.MessageDigest;

public class UtilFuncs {
	public static String sha256(final String base) {
	    try{
	    	if (base!=null) {
	    		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        final byte[] hash = digest.digest(base.getBytes("UTF-8"));
		        final StringBuilder hexString = new StringBuilder();
		        for (int i = 0; i < hash.length; i++) {
		            final String hex = Integer.toHexString(0xff & hash[i]);
		            if(hex.length() == 1) 
		              hexString.append('0');
		            hexString.append(hex);
		        }
		        return hexString.toString();
	    	}
	    	else {
	    		return null;
	    	}
	        
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
}
