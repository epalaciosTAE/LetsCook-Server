package com.tae.letscook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tae.letscook.constants.Constants;
import com.tae.letscook.model.GeocodingLatLng;
import com.tae.letscook.model.geocoding.Geocoding;
import com.tae.letscook.service.GeocodingService;

@Controller
public class GeocodingController {

	@Autowired
	GeocodingService geocodingService;

	@RequestMapping(value=Constants.GEOCODING, method=RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody Geocoding getGeoCoding (@RequestParam(value="geocoding")String query){ 
		System.out.println("GeoCoding Controller - query: " + query);
		return geocodingService.getGeoCode(query);
	}
}
