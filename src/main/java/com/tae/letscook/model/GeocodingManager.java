package com.tae.letscook.model;

import java.util.List;

import com.tae.letscook.model.geocoding.Geocoding;

public class GeocodingManager { // DELETE IF NOT USED

	private static GeocodingManager instance;
	private List<Geocoding> locations;
	
	private GeocodingManager (){
		
	}
	
	public static GeocodingManager getInstance (){
		if(instance == null){
			instance = new GeocodingManager();
		}
		return instance;
	}

	public List<Geocoding> getLocations() {
		return locations;
	}

	public void setLocations(List<Geocoding> locations) {
		this.locations = locations;
	}
	
	
}
