package com.myapps.plantwithmind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.Payment;
import com.myapps.plantwithmind.model.SiteUser;
import com.myapps.plantwithmind.repository.PaymentRepo;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepo paymentRepo;
	
	public void save(Payment payment){
		paymentRepo.save(payment);
	}
	
	public Optional<Payment> findPaymentById(Long id){
		return paymentRepo.findById(id);
	}
	public List<Payment> findPaymentsByLivestock(Long id){
		return paymentRepo.findByLivestockId(id);
	}
	
	public List<Payment> getPaymentByUserByLivestock(Long id,Livestock livestock){
		return paymentRepo.findByUser_idAndLivestock(id,livestock);
	}
	
	public Payment getLastPayment(Long id,Livestock livestock){
		return paymentRepo.findTopByUser_idAndLivestockOrderByCreationDate(id,livestock);
	}
}
