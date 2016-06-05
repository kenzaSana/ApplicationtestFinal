package com.example.kenzack.applicationtest.service;

import android.provider.MediaStore;

import com.example.kenzack.applicationtest.Utils.ImageUtils;
import com.example.kenzack.applicationtest.model.FriendShipState;
import com.example.kenzack.applicationtest.model.Friendship;
import com.example.kenzack.applicationtest.model.Image;
import com.example.kenzack.applicationtest.model.Utilisateur;

import java.util.List;

/**
 * Created by KenZack on 17/05/2016.
 */
public class ImageStorageService extends AbstractService {

    public ImageStorageService(){
        super();
    }

    public void storeImage(Utilisateur utilisateur,String filePath){
        try{
            byte[] imageEnBinaire = ImageUtils.getByteArrayFromImage(filePath);
            Image image = new Image();
            image.setNom("test");
            image.setImage(imageEnBinaire);
            image.setProprietaire(utilisateur);
            String[] pathTokens = filePath.split("\\.");
            String extension = pathTokens[pathTokens.length-1];
            image.setExtension(extension);
            imageDao.create(image);}
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String[] getIdImages(Utilisateur utilisateur) {
        String[] idImages = null;
        try {
            List<Image> images = imageDao.queryBuilder().selectColumns("id").where().eq("proprietaire_id",utilisateur.getId()).query();
            idImages = new String[images.size()];
            int i = 0;
            for(Image image : images) {
                idImages[i++] = image.getId()+"";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return idImages;
        }
    }

    public Image getImageBytes(int id) {
        Image image = null;
        try {
            image = imageDao.queryBuilder().where().eq("id",id).queryForFirst();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return image;
        }
    }
    public void deleteImage(int id) {
        try {
            imageDao.deleteById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
