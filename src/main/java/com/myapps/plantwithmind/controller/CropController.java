package com.myapps.plantwithmind.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myapps.plantwithmind.model.Crop;
import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.model.SiteUser;
import com.myapps.plantwithmind.service.CropService;
import com.myapps.plantwithmind.service.LivestockService;

@Controller
public class CropController {
	@Autowired
	private CropService cropService;
	
	@Autowired
	private LivestockService livestockService;

//	@Autowired
//	private ImageService imageService;
	
//	@PostMapping("/addcrop")
//	public String addCrop(@ModelAttribute Crop crop, 
//	  Model model) {
//
//        Crop othercrop = new Crop();
//	    othercrop.setName(crop.getName());
//	    othercrop.setCostOfHerbicide(crop.getCostOfHerbicide());
//	    othercrop.setCostOfPesticide(crop.getCostOfPesticide());
//	    othercrop.setCostOfLandPreparation(crop.getCostOfLandPreparation());
//	    othercrop.setCostOfFertilizer(crop.getCostOfFertilizer());
//	    othercrop.setDescription(crop.getDescription());
//	    model.addAttribute("crops", cropService.findAllCrops());
//	    return "cropmanage";
//	}
	
	@PostMapping("/addlivestock")
	public String addLivestock(@Valid @ModelAttribute Livestock livestock,
			BindingResult result, Model model) {

		if(result.hasErrors()){
			return "addlivestock";
		}
//		Livestock existing = livestockService.findLivestockByName(livestock.getName());
//		if(livestockService.exists(existing.getId()))
        livestockService.save(livestock);
	    model.addAttribute("livestocks", livestockService.findAllLivestocks());
	    return "cropmanage";
	}
	
	@GetMapping("/cropimage/{id}")
	public String showUploadForm(@PathVariable Long id,Model model){
		Crop crop = cropService.findCropById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid crop Id:" + id));
		model.addAttribute("crop",crop);
		return "showuploadform";
	}
	
	@GetMapping("/livestockimage/{id}")
	public String showLiveUploadForm(@PathVariable Long id,Model model){
		Livestock livestock = livestockService.findLivestockById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid livestock Id:" + id));
		model.addAttribute("livestock",livestock);
		return "showuploadform";
	}
	
//	@PostMapping("/livestockimage/{id}")
//    public String handleImagePost(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
//
//        imageService.saveImageFile(id, file);
//
//        return "redirect:addlivestock";
//    }
	
//	 @GetMapping("renderimage/{id}")
//	    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
//		 Livestock livestock = livestockService.findLivestockById(id)
//			      .orElseThrow(() -> new IllegalArgumentException("Invalid livestock Id:" + id));
//
//	        if (livestock.getImage() != null) {
//	            byte[] byteArray = new byte[livestock.getImage().length];
//	            int i = 0;
//
//	            for (Byte wrappedByte : livestock.getImage()){
//	                byteArray[i++] = wrappedByte; 
//	            }
//
//	            response.setContentType("image/jpg");
//	            InputStream is = new ByteArrayInputStream(byteArray);
//	            IOUtils.copy(is, response.getOutputStream());
//	        }
//	    }
	 
	@PostMapping("/updatelivestock/{id}")
	public String updateLivestock(@Valid @ModelAttribute Livestock livestock,@PathVariable("id") Long id,
			Model model,BindingResult result) {
		
		if(result.hasErrors()){
			return "editlivestock";
		}

		Livestock otherlivestock = livestockService.findLivestockById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid livestock Id:" + id));
		otherlivestock.setName(livestock.getName());
	    otherlivestock.setFirstPay(livestock.getFirstPay());
	    otherlivestock.setSecondPay(livestock.getSecondPay());
	    otherlivestock.setThirdPay(livestock.getThirdPay());
	    //otherlivestock.setInterval(livestock.getInterval());
	    otherlivestock.setDescription(livestock.getDescription());
	    model.addAttribute("livestocks", livestockService.findAllLivestocks());
	    return "cropmanage";
	}
	
	@PostMapping("/updatecrop/{id}")
	public String updateCrop(@PathVariable("id") Long id,@ModelAttribute Crop crop, 
	  Model model) {

        Crop othercrop = new Crop();
	    othercrop.setName(crop.getName());
	    othercrop.setCostOfHerbicide(crop.getCostOfHerbicide());
	    othercrop.setCostOfPesticide(crop.getCostOfPesticide());
	    othercrop.setCostOfLandPreparation(crop.getCostOfLandPreparation());
	    othercrop.setCostOfFertilizer(crop.getCostOfFertilizer());
	    othercrop.setDescription(crop.getDescription());
	    model.addAttribute("crops", cropService.findAllCrops());
	    return "cropmanage";
	}
	
	@GetMapping("/editcrop/{id}")
	public String showCropUpdateForm(@PathVariable("id") long id, Model model) {
	    Crop crop = cropService.findCropById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	     
	    model.addAttribute("crop", crop);
	    return "editcrop";
	}
	
	@GetMapping("/editlivestock/{id}")
	public String showLivestockUpdateForm(@PathVariable("id") long id, Model model) {
		Livestock livestock = livestockService.findLivestockById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid livestock Id:" + id));
	     
	    model.addAttribute("livestock", livestock);
	    return "editlivestock";
	}
	
	@GetMapping("/showaddcrop")
	public String showAddCrop(Model model){
		Crop crop = new Crop();
		model.addAttribute("crop",crop);
		return "addcrop";
	}
	@GetMapping("/showaddlivestock")
	public String showAddLivestock(Model model){
		Livestock livestock = new Livestock();
		model.addAttribute("livestock",livestock);
		return "addlivestock";
	}
	@GetMapping("/deletecrop/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
	    Crop crop = cropService.findCropById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid crop Id:" + id));
	    cropService.delete(crop);
	    model.addAttribute("crops", cropService.findAllCrops());
	    return "cropmanage";
	}
	
	@GetMapping("/deletelivestock/{id}")
	public String deleteLivestock(@PathVariable("id") long id, Model model) {
		Livestock livestock = livestockService.findLivestockById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid livestock Id:" + id));
	    livestockService.delete(livestock);
	    model.addAttribute("livestocks", livestockService.findAllLivestocks());
	    return "cropmanage";
	}
}
