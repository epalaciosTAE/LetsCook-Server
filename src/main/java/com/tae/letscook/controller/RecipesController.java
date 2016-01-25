package com.tae.letscook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tae.letscook.constants.Constants;
import com.tae.letscook.model.edaman.Hit;
import com.tae.letscook.model.edaman.Recipe;
import com.tae.letscook.service.RecipesService;

@Controller
public class RecipesController {
	
	@Autowired
	RecipesService recipeService;

	@RequestMapping(value=Constants.FIND_RECIPE_ENDPOINT, method=RequestMethod.GET)
	public @ResponseBody List<Hit> getRecipe(@RequestParam(value="category")String query){ // TODO FIXME THIS IS NULL CHECK try to rename this method = to the client method
		System.out.println("Recipe Controller - query: " + query);
		return recipeService.getRecipe(query);
	}
	
	@RequestMapping (value=Constants.RECIPES_ENDPOINT, method=RequestMethod.GET)
	public @ResponseBody List<Recipe> getRecipes(){
		
		System.out.println("Recipe Controller - Random recipes: " );
		return recipeService.getRecipes();
	}
	
	
}
