package com.myapps.plantwithmind.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.SiteUser;

@Repository
public interface LivestockRepo extends CrudRepository<Livestock,Long> {
     Livestock  findByName(String name);
     Livestock  findByUser(SiteUser user);
}
