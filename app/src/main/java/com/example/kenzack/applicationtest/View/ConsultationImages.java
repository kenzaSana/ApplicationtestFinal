package com.example.kenzack.applicationtest.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.Utils.FileUtils;
import com.example.kenzack.applicationtest.model.Image;
import com.example.kenzack.applicationtest.model.MyApplication;
import com.example.kenzack.applicationtest.model.Session;
import com.example.kenzack.applicationtest.service.FriendsManagementService;
import com.example.kenzack.applicationtest.service.ImageStorageService;
import com.example.kenzack.applicationtest.service.UtilisateurManagementService;

import java.io.File;
import java.io.FileOutputStream;

public class ConsultationImages extends Activity {


    ListView listView;
    RelativeLayout consulterImagesView;
    UtilisateurManagementService uMS = new UtilisateurManagementService();
    ImageStorageService iSS = new ImageStorageService();
    String[] idImages_array;

    private Session getSession(){
        MyApplication myApplication = (MyApplication)this.getApplication();
        return myApplication.getSession();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        consulterImagesView = (RelativeLayout) RelativeLayout.inflate(this,R.layout.activity_consultation_image,null);
        listView =(ListView) consulterImagesView.findViewById(R.id.listViewConsultationImage);
        idImages_array = iSS.getIdImages(getSession().getUtilisateur());
        RowAdapter adapter=new RowAdapter(this,idImages_array);
        listView.setAdapter(adapter);
        Log.i("yes", "getmain");
        setContentView(consulterImagesView);

    }

    class RowAdapter extends ArrayAdapter<String> {
        Context context;
        public RowAdapter(Context context, String[] ids) {
            super(context,R.layout.ligne_consultation_image,R.id.nomImage,ids);
            this.context = context;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.i("position",position+"");
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ligne_consultation_image,parent,false);
            Button buttonTelecharger =(Button) convertView.findViewById(R.id.buttonTelecharger);
            buttonTelecharger.setId(position);
            buttonTelecharger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id_image = idImages_array[position];
                    int id_image_int = Integer.parseInt(id_image);
                    Image image = iSS.getImageBytes(id_image_int);
                    String downloads_path = getGalleryPath();
                    createImageFile(downloads_path,image);
                    restartActivity();
                }
            });

            Button buttonSupprimer =(Button) convertView.findViewById(R.id.buttonSupprimer);
            buttonSupprimer.setId(position);
            buttonSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id_image = idImages_array[position];
                    int id_image_int = Integer.parseInt(id_image);
                    iSS.deleteImage(id_image_int);
                    restartActivity();
                }
            });
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageFromDb);
            String id_image = idImages_array[position];
            int id_image_int = Integer.parseInt(id_image);
            Image image = iSS.getImageBytes(id_image_int);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image.getImage(),0,image.getImage().length);
            imageView.setImageBitmap(bitmap);
            return super.getView(position,convertView,parent);
        }
        //redémarrer l'activité pour rafraichir la liste
        private void restartActivity() {
            ConsultationImages.this.finish();
            ConsultationImages.this.startActivity(getIntent());
        }
    }

    private String getGalleryPath() {
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/";
    }

    private void createImageFile(String path,Image image) {
        FileOutputStream fileOuputStream = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image.getImage(),0,image.getImage().length);
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, image.getNom() , image.getProprietaire().getLogin());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
