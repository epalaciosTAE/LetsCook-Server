package com.tae.letscook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tae.letscook.model.geocoding.Geocoding;
import com.tae.letscook.utils.UrlParamsUtils;

@Service("GeocodingService")
public class GeocodingService {

	private final static String GEOCODING_API_ENDPOINT ="https://maps.googleapis.com/maps/api/geocode/json?address={query}&key=AIzaSyDFsBMi8YyufbdL7MvmvYprGU4jC4BDSGI";


	@Autowired
	RestTemplate restemplate;
	
	public Geocoding getGeoCode(String query) {
		return getGeocodingFromApi(query);
	}

	private Geocoding getGeocodingFromApi(String query) {
		return restemplate.getForObject(GEOCODING_API_ENDPOINT, Geocoding.class, UrlParamsUtils.getUrlParams(query));
	}
	

	

}
