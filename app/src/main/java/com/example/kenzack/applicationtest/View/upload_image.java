package com.example.kenzack.applicationtest.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.service.ImageStorageService;

public class upload_image extends Activity implements View.OnClickListener {

    private static int LAOD_IMage_Results=1;
    static final int Reqest_image_capture=1;
    private Button gallerie,appareil,publier;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        image=(ImageView)findViewById(R.id.imageView);
        gallerie=(Button)findViewById(R.id.button2);
        appareil=(Button)findViewById(R.id.button9);

        //if(!hasCamera())
            // appareil.setEnabled(false);

        appareil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,Reqest_image_capture);

            }
        });
        publier=(Button)findViewById(R.id.button10);
        publier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(upload_image.this, com.example.kenzack.applicationtest.View.publier.class);

                startActivity(intent);

            }
        });
        gallerie.setOnClickListener(this);

    }


    //private boolean hasCamera(){
    //    return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    //}


    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (requestCode == LAOD_IMage_Results && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
           final  String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            image.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            final Drawable drawable = image.getDrawable();
            cursor.close();
            publier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(upload_image.this, com.example.kenzack.applicationtest.View.publier.class);
                    ImageStorageService iSS = new ImageStorageService() ;
                    iSS.storeImage(imagePath);
                    startActivity(intent);

                }
            });


        }   //else if(requestCode==Reqest_image_capture && resultCode==RESULT_OK){
                //Bundle extras=data.getExtras();
              //  Bitmap photo=(Bitmap)extras.get("data");
               // image.setImageBitmap(photo);


        //}

    }

    @Override
    public void onClick(View v) {
        Intent i =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i,LAOD_IMage_Results);

    }
}
