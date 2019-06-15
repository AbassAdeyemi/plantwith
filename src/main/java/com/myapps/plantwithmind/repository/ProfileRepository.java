package com.myapps.plantwithmind.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapps.plantwithmind.model.Profile;
import com.myapps.plantwithmind.model.SiteUser;

@Repository 
public interface ProfileRepository extends CrudRepository<Profile,Long>{
	Profile findByUser(SiteUser user);
       
}
