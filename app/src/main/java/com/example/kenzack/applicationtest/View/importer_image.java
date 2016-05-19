package com.example.kenzack.applicationtest.View;
// c'est la premier activitée mmeme si son nom est importer_image car je me suis trompé au début et j'ai pa su comment la changé.
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kenzack.applicationtest.R;

public class importer_image extends AppCompatActivity {
    final String Extra_login="user_login";
    Button cnx,insc;
    TextView t1,t2;
    EditText login , pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importer_image);
        login=(EditText)findViewById(R.id.editText);
       cnx=(Button)findViewById(R.id.button2);
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inetent permet de passer d'une activité a une autre
                Intent intent = new Intent(importer_image.this,acceuil.class);
                //putExtra permet d'inserer qlq chose dans l autre activité , ici je vous qu'eele se rappelle du login
                intent.putExtra(Extra_login,login.getText().toString());
                startActivity(intent);
            }
        });
        insc=(Button)findViewById(R.id.button3);
        insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(importer_image.this,formulaire.class);
                startActivity(intent);
            }
        });

        pass=(EditText)findViewById(R.id.editText2);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);

    }
}
