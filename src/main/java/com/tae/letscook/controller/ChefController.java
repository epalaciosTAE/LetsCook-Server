package com.tae.letscook.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tae.letscook.constants.Constants;
import com.tae.letscook.model.Chef;
import com.tae.letscook.service.ChefService;
import com.tae.letscook.service.SignInService;

@Controller
public class ChefController {

	
	@Autowired
	private ChefService chefService;
	
	@RequestMapping (value=Constants.CHEF, method=RequestMethod.POST)
	public @ResponseBody Chef getRecipes(@RequestBody Chef chef, HttpServletResponse response){
		System.out.println("Chef Controller - chef to save: " + chef.getName());
		Chef savedChef = chefService.saveChef(chef);
		if(savedChef == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		return savedChef;
	}
	
	@RequestMapping (value=Constants.CHEF, method=RequestMethod.GET)
	public @ResponseBody Chef getRecipes(){
		System.out.println("Chef Controller -get chef: ");
		Chef chef = chefService.getChef();
		return chef;
	}
	
	
	
}
