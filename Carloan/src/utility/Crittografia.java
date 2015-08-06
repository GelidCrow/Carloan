package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Crittografia {
	private String ciao;
	public String CriptaPassword(String password,String salt){
		 String generatedPassword = null;
         try {
       	  MessageDigest md = MessageDigest.getInstance("SHA1");
             md.update(salt.getBytes());
             byte[] bytes = md.digest(password.getBytes());
             StringBuilder sb = new StringBuilder();
             for(int i=0; i< bytes.length ;i++)
             {
                 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
             }
             generatedPassword = sb.toString();
         }
         catch (NoSuchAlgorithmException e)
         {
             e.printStackTrace();
         }
         return generatedPassword;
	}
	public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException{
		//Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        return salt.toString();
	}
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException{
		Crittografia x= new Crittografia();
		System.out.println(x.CriptaPassword("Ciao", Crittografia.getSalt()));
		System.out.println(x.CriptaPassword("Password", Crittografia.getSalt()));
		System.out.println(x.CriptaPassword("PassworD", Crittografia.getSalt()));
	}
}

