package com.product.util;

public enum EnumConstants {

	// signin
	
		INVALID_USER {
		      public String toString() {
		          return "{\"message\" : \"User details not found \" , \"result\" : false}";
		      }
		  },
		 
		  INVALID_PASSWORD {
		      public String toString() {
		          return "{\"message\" : \"Please enter  Correct Password\" ,\"result\" : false}";
		      }
		  },
		  
		  // empty input request body
		  
		  EMPTY_JSON_STRING{
			  public String toString() {
		          return "{\"message\" : \"Input Request has empty Values\" ,\"result\" : false}";
		      }
		  },
		  
}
