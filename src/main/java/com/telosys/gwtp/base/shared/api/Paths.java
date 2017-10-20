package com.telosys.gwtp.base.shared.api;

public class Paths {
	
	private Paths(){
	}

	public static final String BASE_URL = "http://localhost:8080/league-factory-api-0.1";

	public static final String API_ROOT = "/api/v1";
	public static final String PLAYER = "/player";
	public static final String TEAM = "/team";

	public static final String ALLOWED_HEADERS = "Content-Type,Access-Control-Allow-Origin, Access-Control-Allow-Headers, Authorization, X-Requested-With,x-gwt-module-base";
	public static final String ALLOWED_METHOD = "GET,POST,PUT,DELETE,OPTIONS";
	public static final String ALLOWED_ORIGIN = "*";

}
