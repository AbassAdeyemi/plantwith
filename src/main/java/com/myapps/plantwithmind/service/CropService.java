package com.myapps.plantwithmind.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapps.plantwithmind.model.Crop;
import com.myapps.plantwithmind.repository.CropRepository;

@Service
public class CropService {

	@Autowired
	private CropRepository cropRepository;

	public void save(Crop crop){
		cropRepository.save(crop);
	}
	
	public Crop findCropByName(String name){
		return cropRepository.findByName(name);
	}
	
	public Iterable<Crop> findAllCrops(){
		return cropRepository.findAll();
	}
	public Optional<Crop> findCropById(Long id){
		return cropRepository.findById(id);
	}

	public void delete(Crop crop) {
		cropRepository.delete(crop);
		
	}
}
