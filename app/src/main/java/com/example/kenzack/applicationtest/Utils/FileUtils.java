package com.example.kenzack.applicationtest.Utils;

import com.example.kenzack.applicationtest.model.Image;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by aziouiz on 05/06/16.
 */
public class FileUtils {
    public static void createImageFile(String path,Image image) {
        FileOutputStream fileOuputStream = null;
        try {
            File file = new File(path+System.currentTimeMillis()+".png");
            fileOuputStream = new FileOutputStream(file);
            fileOuputStream.write(image.getImage());
            fileOuputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
