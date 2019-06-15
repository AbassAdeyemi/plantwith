package com.myapps.plantwithmind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapps.plantwithmind.model.Profile;
import com.myapps.plantwithmind.model.SiteUser;
import com.myapps.plantwithmind.repository.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	private ProfileRepository profileRepository;

	public void save(Profile profile){
		profileRepository.save(profile);
	}
	
	public Profile getUserProfile(SiteUser user){
		return profileRepository.findByUser(user);
	}
}
