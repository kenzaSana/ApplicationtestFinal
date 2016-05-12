package com.example.kenzack.applicationtest.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kenzack.applicationtest.R;

public class publier extends AppCompatActivity {

    TextView t;
    Button acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.activity_publier);

        t=(TextView)findViewById(R.id.textView9);
        acc=(Button)findViewById(R.id.button12);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(publier.this,acceuil.class);
                startActivity(in);
            }
        });
    }

}
