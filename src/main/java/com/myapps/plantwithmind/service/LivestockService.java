package com.myapps.plantwithmind.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.SiteUser;
import com.myapps.plantwithmind.repository.LivestockRepo;

@Service
public class LivestockService {

	@Autowired
	private LivestockRepo livestockRepo;

	public void save(Livestock livestock){
		livestockRepo.save(livestock);
	}
	
	public Livestock findLivestockByName(String name){
		return livestockRepo.findByName(name);
	}
	
	public Livestock findLivestockByUser(SiteUser user){
		return livestockRepo.findByUser(user);
	}
	
	public Iterable<Livestock> findAllLivestocks(){
		return livestockRepo.findAll();
	}
	public Optional<Livestock> findLivestockById(Long id){
		return livestockRepo.findById(id);
	}

	public void delete(Livestock livestock) {
		livestockRepo.delete(livestock);		
	}
	
	public boolean exists(Long id) {
		return livestockRepo.existsById(id);
		
	}
}
