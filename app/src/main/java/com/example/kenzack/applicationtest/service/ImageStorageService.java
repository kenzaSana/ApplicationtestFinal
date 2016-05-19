package com.example.kenzack.applicationtest.service;

import com.example.kenzack.applicationtest.Utils.ImageUtils;
import com.example.kenzack.applicationtest.model.Image;

/**
 * Created by KenZack on 17/05/2016.
 */
public class ImageStorageService extends AbstractService {

    public ImageStorageService(){
        super();
    }

    public void storeImage(String filePath){
        try{

            byte[] imageEnBinaire = ImageUtils.getByteArrayFromImage(filePath);
            Image image = new Image();
            image.setNom("test");
            image.setImage(imageEnBinaire);
            imageDao.create(image);}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
