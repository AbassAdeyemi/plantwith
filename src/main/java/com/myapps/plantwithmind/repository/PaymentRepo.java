package com.myapps.plantwithmind.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.Payment;

@Repository
public interface PaymentRepo extends CrudRepository<Payment,Long> {
 List<Payment> findByLivestockId(Long id);
 List<Payment> findByUser_idAndLivestock(Long id,Livestock livestock);
 Payment findTopByUser_idAndLivestockOrderByCreationDate(Long id,Livestock livestock);
}
