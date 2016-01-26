package com.tae.letscook.constants;

public class Constants {
	
	public final static String FIND_RECIPE_ENDPOINT= "/find-recipe";
	public final static String RECIPES_ENDPOINT= "/recipes";
//	public final static String FIND_RECIPE_ENDPOINT= "/category/{category}";
	public static final String SIGN_IN_ENDPOINT = "/sign-in/authcode";
	
	
	public static final String CLIENT_SECRET = "src/main/resources/client_secret_604841599359-1sbc1hn16c1i7csrrgntjh03lv6g7r6a.apps.googleusercontent.com.json";
	public static final String GEOCODING = "/geocoding";
	
	public final static String QUERY = "query";
	public static final String CHEF = "/chef";
	public static final String EVENT = CHEF +  "/{id}/event";
    public static final String EVENTS = CHEF + "/events";

}
