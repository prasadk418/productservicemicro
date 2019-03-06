package com.product.util;



public class PasswordEncryption {

	
	public static String encryptPwd(String pwd) {

        return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
        }

	public static Boolean checkPwd(String pwd, String dbPwd)
	{
		return BCrypt.checkpw(pwd, dbPwd);
	}
	
	
	/*public static void main(String[] args) {
		PasswordEncryption p=new PasswordEncryption();
		String h=p.encryptPwd("dada");
		System.out.println(h);
		boolean bb=p.checkPwd("dada", h);
		System.out.println(bb);
	}*/
}
