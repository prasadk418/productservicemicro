package com.product.util;

import java.util.Optional;

public class ProductUtil {

	public ProductUtil() {

	}
	
	public static String buildUrl(LoadProperties loadProperties){
		return loadProperties.getProtocol() + "://"
				+ loadProperties.getHost() + ":" + loadProperties.getPort();
	}

	public static <T> boolean isNotPresent(Optional<T> optional){
		return !optional.isPresent();
	}


}
