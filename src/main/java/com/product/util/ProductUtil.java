package com.product.util;

public class ProductUtil {

	public ProductUtil() {

	}
	
	public static String buildUrl(LoadProperties loadProperties){
		return loadProperties.getProtocol() + "://"
				+ loadProperties.getHost() + ":" + loadProperties.getPort();
	}


}
