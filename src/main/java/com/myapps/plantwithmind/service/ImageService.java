package com.myapps.plantwithmind.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myapps.plantwithmind.model.Crop;
import com.myapps.plantwithmind.model.Livestock;
import com.myapps.plantwithmind.repository.CropRepository;
import com.myapps.plantwithmind.repository.LivestockRepo;

@Service
public class ImageService {

	@Autowired
    private CropRepository cropRepository;
	
	@Autowired
    private LivestockRepo livestockRepo;
	
	@Transactional
	public void saveImageFile(Long cropId, MultipartFile file){
		
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> i got here");
            Livestock livestock = livestockRepo.findById(cropId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+byteObjects.toString());
            livestock.setImage(byteObjects);

            livestockRepo.save(livestock);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
