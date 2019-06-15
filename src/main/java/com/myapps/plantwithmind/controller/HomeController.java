package com.myapps.plantwithmind.controller;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapps.plantwithmind.model.Crop;
import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.SiteUser;
import com.myapps.plantwithmind.service.CropService;
import com.myapps.plantwithmind.service.LivestockService;
import com.myapps.plantwithmind.service.UserService;

@Controller
public class HomeController {
	
	private UserService userService;

	@Autowired
	private CropService cropService;
	
	@Autowired
	private LivestockService livestockService;
	
	@Autowired
	public HomeController(UserService userService){
		this.userService = userService;
	}


	@GetMapping("/")
	public String showHome(){
		return "index";
	}
	
	@GetMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@GetMapping("/manageuser")
	public String showManageUser(Model model){
		Iterable<SiteUser> users = userService.findAll();
		System.out.println(users);
		model.addAttribute("users",users);
		return "usermanage";
	}
	
	@GetMapping("/managelivestock")
	public String showManageCrop(Model model){
		Iterable<Livestock> livestocks = livestockService.findAllLivestocks();
		System.out.println(livestocks);
		model.addAttribute("livestocks",livestocks);
		return "cropmanage";
	}
	
//	@GetMapping("/crops/{anycrop}")
//	public String showCrop(@PathVariable String anycrop,Model model){
//		System.out.println(">>>>>>>>>>>>>>>>"+anycrop);
//		Crop crop = cropService.findCropByName(anycrop);
//		if(crop == null){
//			crop = new Crop(anycrop);
//			cropService.save(crop);
//		}
//		crop.setFirstPay("100000");
//		crop.setSecondPay("100000");
//		crop.setThirdPay("100000");
//		cropService.save(crop);
//		model.addAttribute("crop",crop);
//		return anycrop;
//	}
	
	@GetMapping("/crops")
	public String showCrops(){
		return "crops";
	}
	
	
	@GetMapping("/about")
	public String showAbout(){
		return "about";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model){
		SiteUser user = new SiteUser();
		model.addAttribute("user",user);
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") SiteUser user,BindingResult result,Model model) {
		
		if(result.hasErrors()){
			return "register";
		}
		userService.register(user);
		model.addAttribute("user", user);
		return "index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    SiteUser user = userService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	     
	    model.addAttribute("user", user);
	    return "edituser";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") Long id,@ModelAttribute SiteUser user, 
	  Model model) {
	    SiteUser savedUser = userService.findById(id)
	  	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));  
	    savedUser.setFirstname(user.getFirstname());
	    savedUser.setLastname(user.getLastname());
	    savedUser.setEmail(user.getEmail());
	    savedUser.setPhonenumber(user.getPhonenumber());
	    savedUser.setAddress(user.getAddress());
	    savedUser.setState(user.getState());
	    savedUser.setLga(user.getLga());
	    userService.save(savedUser);
	    model.addAttribute("users", userService.findAll());
	    return "usermanage";
	}
	     
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
	    SiteUser user = userService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    userService.delete(user);
	    model.addAttribute("users", userService.findAll());
	    return "usermanage";
	}
	
}
