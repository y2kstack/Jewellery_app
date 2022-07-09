package com.tutorialsee;


public class Constant {


	
	public static String REG = "";
	
	
	 public static String getCountryByCode(String code) {
	        int i = -1;
	        for (String cc:MainActivity.context.getResources().getStringArray(R.array.codes)) {
	            i++;
	            if (cc.equals(code))
	                break;
	        }
	        return MainActivity.context.getResources().getStringArray(R.array.names)[i];
	    }
	

	
	
}

