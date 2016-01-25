package com.tae.letscook.utils;

import java.util.ArrayList;
import java.util.List;

public class RecipesGenerator {
	
	/**
	 * Just a fake generator for types of food.
	 * @return
	 */
	public static List<String> fakeFoodTypeGenerator() {
		List<String> querys = new ArrayList<>(10);
		querys.add("fish");
		querys.add("chicken");
//		querys.add("beef");
//		querys.add("rice");
//		querys.add("pasta");
//		querys.add("soup");
//		querys.add("seafood");
//		querys.add("cakes");
//		querys.add("curry");
//		querys.add("fruit");
//		querys.add("pork");
		return querys;
	}

}
