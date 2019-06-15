package com.myapps.plantwithmind.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapps.plantwithmind.model.Crop;

@Repository
public interface CropRepository extends CrudRepository<Crop,Long>{
      Crop findByName(String name);
	
}
