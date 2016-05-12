package com.example.kenzack.applicationtest.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kenzack.applicationtest.R;

public class upload_image extends AppCompatActivity implements View.OnClickListener {

    private static int LAOD_IMage_Results=1;
    private Button gallerie,appareil,publier;
    private ImageView image;
    // private Uri image2;

    String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        image=(ImageView)findViewById(R.id.imageView);
        gallerie=(Button)findViewById(R.id.button2);
        appareil=(Button)findViewById(R.id.button9);
        appareil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(upload_image.this,webcam.class);

                startActivity(intent);

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
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAOD_IMage_Results && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            image.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            final Drawable drawable = image.getDrawable();
            cursor.close();
            //inserer_image("pic",cursor.getString(cursor.getColumnIndex(filePath[0])));

        }

    }

    @Override
    public void onClick(View v) {
        Intent i =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i,LAOD_IMage_Results);

    }
}
