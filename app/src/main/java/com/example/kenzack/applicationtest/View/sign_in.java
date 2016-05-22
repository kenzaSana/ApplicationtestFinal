package com.example.kenzack.applicationtest.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kenzack.applicationtest.R;

public class sign_in extends AppCompatActivity {
    Button next;
    EditText nom,tel;
     ImageView profil;
    String name,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
