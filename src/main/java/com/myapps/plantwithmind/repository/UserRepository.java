package com.myapps.plantwithmind.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapps.plantwithmind.model.SiteUser;

@Repository
public interface UserRepository extends CrudRepository<SiteUser,Long>{
	SiteUser findByEmail(String email);

}
