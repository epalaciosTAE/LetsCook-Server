package com.tae.letscook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tae.letscook.model.Chef;
import com.tae.letscook.repository.ChefRepository;

@Service("ChefService")
public class ChefService {

	@Autowired
	ChefRepository repo;
	
	public Chef saveChef(Chef chef){
		System.out.println("ChefService - saving user ");
		return repo.save(chef);
	}

	public Chef getChef() {
		return repo.findOne((long) 1);
	}
		
}
