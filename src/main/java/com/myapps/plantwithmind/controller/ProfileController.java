package com.myapps.plantwithmind.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myapps.plantwithmind.service.CropService;
import com.myapps.plantwithmind.service.LivestockService;
import com.myapps.plantwithmind.service.PaymentService;
import com.myapps.plantwithmind.service.ProfileService;
import com.myapps.plantwithmind.service.UserService;
import com.myapps.plantwithmind.model.Crop;
import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.Payment;
import com.myapps.plantwithmind.model.Profile;
import com.myapps.plantwithmind.model.SiteUser;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private LivestockService livestockService;
	
	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+email);
		return userService.findByEmail(email);
	}
	
	@GetMapping("/livestocks/{anylivestock}")
	public String showLivestock(@PathVariable String anylivestock,Model model){
		System.out.println(">>>>>>>>>>>>>>>>"+anylivestock);
		SiteUser user = getUser();
		Livestock livestock = new Livestock(anylivestock);
		if(user == null){
		 livestock = livestockService.findLivestockByName(anylivestock);
		if(livestock == null){
			livestock = new Livestock(anylivestock);
			livestockService.save(livestock);
		}
		}
		else if(livestockService.findLivestockByUser(user) != null){
			livestock = livestockService.findLivestockByUser(user);
		}
		livestock.setFirstPay("100000");
		livestock.setSecondPay("100000");
		livestock.setThirdPay("100000");
		livestockService.save(livestock);
		model.addAttribute("livestock",livestock);
		return anylivestock;
	}
	
	@PatchMapping(value="/search")
	public ModelAndView search(@RequestParam String cropName){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		return mv;
	}
	
//	private String showProfile(SiteUser user,Model model ){
//		//Payment payment= new Payment();
//		if(user == null) {
//			return "redirect:/";
//		}
//		
//		Profile profile = profileService.getUserProfile(user);
//          Payment payment = profile.getPayment();
//          
//		if (profile == null && payment == null) {
//			profile = new Profile();
//			profile.setUser(user);
//			payment = new Payment();
//			 profile.setPayment(payment);
//			profileService.save(profile);
//		}
//		else{
//			payment = profile.getPayment();
//		}
//		List<SiteUser> iteratedUser = (List)userService.findAll();
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+iteratedUser.size());
//		model.addAttribute("email", user.getEmail());
//		model.addAttribute("userId", user.getId());
//		model.addAttribute("phonenumber", user.getPhonenumber());
//		model.addAttribute("firstname", user.getFirstname());
//		model.addAttribute("lastname", user.getLastname());
//		model.addAttribute("initial", user.getFirstname().toUpperCase().charAt(0));
//		model.addAttribute("numofusers", iteratedUser.size());
//		model.addAttribute("profilelivestocks",profile.getLivestocks());
//		 model.addAttribute("paytype",payment);
//		return "profile";
//	}
	  @GetMapping(value = "/profile")
      private String showProfile(Model model) {
		SiteUser user = getUser();
		List<Payment> payments = new ArrayList<>();
		Payment payment = new Payment();
		Payment duepay = new Payment();
		if(user == null) {
			return "redirect:/";
		}
		Profile profile = profileService.getUserProfile(user);
		if (profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}
		
		 Livestock livestock = livestockService.findLivestockByUser(user);
		    if(livestock!= null){
		    	payments = paymentService.getPaymentByUserByLivestock(user.getId(),livestock);
		    	if(!payments.isEmpty()){
		    		payment = paymentService.getLastPayment(user.getId(),livestock);
		    		duepay = getDuePay(payment,livestock);
		    	}
		    	else{
		    		duepay.setAmount(livestock.getFirstPay());
		    	}
		    }
		List<SiteUser> iteratedUser = (List)userService.findAll();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+iteratedUser.size());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("user",user);
		model.addAttribute("duepay",duepay);
		model.addAttribute("livestock",livestock);
		model.addAttribute("initial", user.getFirstname().toUpperCase().charAt(0));
		return "profile";
	}
	

	@GetMapping(value="/bookcrop/{id}")
	public String savePackage(@PathVariable long id,Model model) {
		List<Payment> payments = new ArrayList<>();
		Payment payment = new Payment();
		Payment duepay = new Payment();
		SiteUser user = getUser();
	    Livestock livestock = livestockService.findLivestockByUser(user);
	    if(livestock!= null){
	    	payments = paymentService.getPaymentByUserByLivestock(user.getId(),livestock);
	    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+payments);
	    	if(!payments.isEmpty()){
	    		payment = paymentService.getLastPayment(user.getId(),livestock);
	    		duepay = getDuePay(payment,livestock);
	    	}
	    	else{
	    		duepay.setAmount(livestock.getFirstPay());
	    	}
	    }
	    else{
    		livestock = new Livestock(livestockService.findLivestockById(id).get().getName());
    		livestock.setUser(user);
    		livestockService.save(livestock);
    	}
  
		Profile profile = profileService.getUserProfile(user);
    	model.addAttribute("livestock",livestock);
        model.addAttribute("user",user);
        model.addAttribute("duepay",duepay);
        model.addAttribute("payments",payments);
        model.addAttribute("initial", user.getFirstname().toUpperCase().charAt(0));
        return "profile";
     
	}
	
    public Payment getDuePay(Payment payment,Livestock livestock){
    	if(payment.getPaymentFlag() == 0){
    		payment.setAmount(livestock.getFirstPay());
    	}
    	else if(payment.getPaymentFlag() == 1){
    		payment.setAmount(livestock.getSecondPay());
    	}
    	else if(payment.getPaymentFlag() == 2){
    		payment.setAmount(livestock.getThirdPay());
    	}
    	return payment;
    }
	
	
	
}
