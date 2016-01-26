package com.tae.letscook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.tae.letscook.constants.Constants;
import com.tae.letscook.manager.RecipeFileManager;
import com.tae.letscook.model.CustomRecipe;
import com.tae.letscook.model.CustomRecipeStatus;
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
	
	
	@RequestMapping(value=Constants.USER_RECIPES, method=RequestMethod.POST)
	public @ResponseBody CustomRecipe addCustomRecipe (@RequestBody CustomRecipe selfie){
		System.out.println("Recipe Controller - post custom recipes: " );
		CustomRecipe selfieToReturn = recipeService.saveCustomRecipe(selfie);
		return selfieToReturn;
	} 
	
	@RequestMapping(value=Constants.USER_RECIPES_DATA, method=RequestMethod.POST)
	public @ResponseBody CustomRecipeStatus setSelfieData(
					@PathVariable(Constants.ID_PARAMETER) long id,
					@RequestParam(Constants.DATA_IMAGE) MultipartFile multipartDataSelfie,
					HttpServletResponse response) throws IOException {
		
		System.out.println("Recipe Controller - post custom recipes: save image in server file" );
		CustomRecipe selfie = recipeService.getRecipeById(id);
		if (selfie != null){
			RecipeFileManager manager = RecipeFileManager.get();
			manager.saveSelfieData(selfie, multipartDataSelfie.getInputStream());

//			recipeService.setData(selfie);
		} else{
			response.setStatus(404);
			return null;
		}
		return new CustomRecipeStatus(CustomRecipeStatus.RecipeState.READY);
	}
	
	@RequestMapping (value=Constants.USER_RECIPES, method=RequestMethod.GET)
	public @ResponseBody List<CustomRecipe> getCustomRecipes(HttpServletResponse response){
		System.out.println("Recipe Controller - Get Custom recipes: " );
		return recipeService.getCustomRecipes();
	}
	
	@RequestMapping (value=Constants.USER_RECIPES_IMAGES, method=RequestMethod.GET)
    public void getCustomRecipesImage(@PathVariable("id") long id, HttpServletResponse response) {
		System.out.println("Recipe Controller - Get Custom recipe images: " );
		CustomRecipe selfie = recipeService.getCustomRecipeById(id);//getSelfieByDeviceId(id);
		if (selfie != null){
			response.setContentType("image/*");
			try {
				if(RecipeFileManager.get().hasSelfieData(selfie)){
					RecipeFileManager.get().copySelfieoData(selfie, response.getOutputStream());
				} else {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (IOException e){
				System.out.println("Recipe Controller - Error copying image data " + e.getMessage());
			}
		}
	

    }
	
	
	
	
}
