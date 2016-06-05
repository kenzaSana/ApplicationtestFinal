package com.example.kenzack.applicationtest.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kenzack.applicationtest.R;

public class acceuil extends AppCompatActivity {
    final String Extra_login="user_login";
    TextView T1,T2,T3;
    Button upload,voir_image,voir_ami,dcnx,ajout_ami,voir_invitations,consulter_images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Intent intent= getIntent();

        T1=(TextView) findViewById(R.id.textView);
        T2=(TextView) findViewById(R.id.textView3);
        dcnx=(Button)findViewById(R.id.button6);
        dcnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirm=new AlertDialog.Builder(acceuil.this);
                confirm.setTitle("Déconnexion");
                confirm.setMessage("Vous confirmez la déconnexion?");
                confirm.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                confirm.setNegativeButton("Non",null);
                confirm.show();
            }
        });
        upload=(Button)findViewById(R.id.button3);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(acceuil.this,upload_image.class);
                startActivity(intent);
            }
        });
        voir_image=(Button)findViewById(R.id.button4);
        voir_ami=(Button)findViewById(R.id.button5);

        ajout_ami=(Button)findViewById(R.id.buttonAjouterAmi);
        ajout_ami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(acceuil.this,AjouterAmi.class);
                startActivity(intent);
            }
        });
        voir_invitations=(Button)findViewById(R.id.buttonVoirInvitations);
        voir_invitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(acceuil.this,Invitations.class);
                startActivity(intent);
            }
        });
        consulter_images=(Button)findViewById(R.id.consulterImageButton);
        consulter_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(acceuil.this,ConsultationImages.class);
                startActivity(intent);
            }
        });

    }
}
