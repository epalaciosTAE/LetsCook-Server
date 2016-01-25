package com.tae.letscook.repository;

import javax.persistence.Table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tae.letscook.model.Chef;

@Repository
@Table(name="chef_table")
public interface ChefRepository extends CrudRepository<Chef, Long>{

	
	 
}
