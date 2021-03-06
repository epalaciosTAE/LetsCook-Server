package com.tae.letscook.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tae.letscook.manager.RecipeFileManager;
import com.tae.letscook.model.CustomIngredient;
import com.tae.letscook.model.CustomRecipe;
import com.tae.letscook.model.ServerRecipe;
import com.tae.letscook.model.edaman.EdamanApiModel;
import com.tae.letscook.model.edaman.Hit;
import com.tae.letscook.model.edaman.Recipe;
import com.tae.letscook.repository.CustomIngredientRepository;
import com.tae.letscook.repository.CustomRecipeRepository;
import com.tae.letscook.utils.RecipesGenerator;
import com.tae.letscook.utils.UrlParamsUtils;

@Service("RecipesService")
public class RecipesService {
	
	private final static String EDAMAN_API_ENDPOINT ="https://api.edamam.com/search?q={query}&to=2&app_id=c13f76f4&app_key=e1ab0d3d48e13585699e64fa2d864baa";
	private final static String QUERY = "query";
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CustomIngredientRepository ingredientRepo;
	
	@Autowired
	CustomRecipeRepository recipeRepo;
	
	/**
	 * getUrlParams creates a map with the user query and the tipe of recipe that is looking for.
	 * E.g: query = rice, omelet, etc.
	 * @param query
	 * @return Map<String, String>
	 */
//	private Map<String, String> getUrlParams(String query) {
//		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put(QUERY, query);
//		return paramMap;
//	}
	
	/**
	 * getRecipeFromAPI hit the endpoint in the API to get one recipe by search.
	 * Use a ResTemplate object for that.
	 * Pass as parameter getUrlParams to add the user query in the url
	 * @param query
	 * @return EdamanApiModel
	 */
	private List<Hit> getRecipeFromAPI(String query) {	
//		restTemplate.getForEntity("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyBwHufGl6yoKw4YKfpRlWALW2-vSPgnCNk", Recipe.class);
		return extractRecipeFromModelApi(restTemplate.getForObject(EDAMAN_API_ENDPOINT, EdamanApiModel.class, UrlParamsUtils.getUrlParams(query)));
	}
	
	/**
	 * Extract the list of recipes from the Api Model
	 * @param apiModel
	 * @return
	 */
	private List<Hit> extractRecipeFromModelApi (EdamanApiModel apiModel){
		return apiModel.getHits();
	}
	
	/**
	 * Method to get a recipe by users search.
	 * Called in RecipeCOntroller
	 * @param query
	 * @return List<Hit> contains a list of Recipe
	 */
	
	public  List<Hit> getRecipe(String query){
		return getRecipeFromAPI(query);
	}
	
	/**
	 * Method to get a list of recipes. 
	 * The API doesn't provide a list of recipe so we will autogenerated. 
	 * @return
	 */
	public List<Recipe> getRecipes (){
		return randomRecipesGenerator();
	}
	
	/**
	 * Generate a random list of recipes 
	 * @return List<Recipe>
	 */
	private List<Recipe> randomRecipesGenerator (){
		List<Recipe> recipesToReturn = new ArrayList<>(2); // TODO change this to 10
		Random random = new Random();
		for(String foodType : RecipesGenerator.fakeFoodTypeGenerator()){
			List<Hit> recipes = getRecipe(foodType);
			Recipe recipe = recipes.get( random.nextInt(recipes.size())).getRecipe();
			recipesToReturn.add(recipe);
		}
//		List<Hit> recipes = getRecipe("chicken"); // Delete this, only for test purpose
//		Recipe recipe = recipes.get(0).getRecipe();
//		recipesToReturn.add(recipe);
		return recipesToReturn;
	}

	public CustomRecipe saveCustomRecipe(CustomRecipe recipe) {
		for(CustomIngredient ingredient : recipe.getIngredients()){
			ingredient.setUserId(recipe.getChefId());
			ingredientRepo.save(ingredient);
		}
		ServerRecipe tempRecipe = recipeRepo.save(new ServerRecipe(
				recipe.getTitle(),
				recipe.getImagePath(),
				recipe.getBudget(),
				recipe.getLevel(),
				recipe.getPeople(),
				recipe.getTime(),
				recipe.getChefName(),
				recipe.getChefId()));
		
		return createCustomRecipe(tempRecipe, recipe.getIngredients());
	}
	
	
	public CustomRecipe getRecipeById(long id) {
		ServerRecipe tempRecipe = recipeRepo.findOne(id);
		return createCustomRecipe(tempRecipe);
	}
	
	private CustomRecipe createCustomRecipe(ServerRecipe tempRecipe) {
		return new CustomRecipe(
				tempRecipe.getId(),
				tempRecipe.getTitle(),
				tempRecipe.getImagePath(),
				tempRecipe.getBudget(),
				tempRecipe.getLevel(),
				tempRecipe.getPeople(), 
				tempRecipe.getTime(),
				tempRecipe.getChefName(),
				tempRecipe.getChefId());
	}

	private CustomRecipe createCustomRecipe(ServerRecipe tempRecipe, List<CustomIngredient> ingredients) {
		return new CustomRecipe(
				tempRecipe.getId(),
				tempRecipe.getTitle(),
				tempRecipe.getImagePath(),
				tempRecipe.getBudget(),
				tempRecipe.getLevel(),
				tempRecipe.getPeople(), 
				tempRecipe.getTime(),
				tempRecipe.getChefName(),
				tempRecipe.getChefId(),
				ingredients);
	}

	public void setData(CustomRecipe recipe) {
//		recipeRepo.save(selfie);
		ServerRecipe tempRecipe = recipeRepo.save(new ServerRecipe(
				recipe.getTitle(),
				recipe.getImagePath(),
				recipe.getBudget(),
				recipe.getLevel(),
				recipe.getPeople(),
				recipe.getTime(),
				recipe.getChefName(),
				recipe.getChefId()));
		
	}

	public List<CustomRecipe> getCustomRecipes() {
		List<ServerRecipe> serverRecipes = convertCollectionToList((Collection<ServerRecipe>) recipeRepo.findAll());
		List<CustomRecipe> recipes = new ArrayList<>(serverRecipes.size());
		for(ServerRecipe serviceRecipe : serverRecipes){
			recipes.add(createCustomRecipe(serviceRecipe));
		}
		List<CustomIngredient> ingredients = convertCollectionToList((Collection<CustomIngredient>) ingredientRepo.findAll());
		for(CustomRecipe recipe : recipes){
			List<CustomIngredient> tempIngredients = new ArrayList<>();
			for(CustomIngredient ingredient : ingredients){
				if(recipe.getChefId().equals(ingredient.getUserId())){
					tempIngredients.add(ingredient);
				}
			}
			try {
				recipe.setImageServerPath(RecipeFileManager.get().getSelfiePath(recipe).toAbsolutePath().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			recipe.setIngredients(tempIngredients);
		}
		return recipes;
	}
	
	private <T>List<T> convertCollectionToList (Collection<T> eventsCol){
		List<T> events = new ArrayList<T>(eventsCol.size());
		for (Iterator iterator = eventsCol.iterator(); iterator.hasNext();) {
		        events.add((T) iterator.next());
		    }
		return events;
	}

	public CustomRecipe getCustomRecipeById(long id) {
		return createCustomRecipe(recipeRepo.findOne(id));
	}

	
}
