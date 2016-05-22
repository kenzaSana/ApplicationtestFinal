package com.example.kenzack.applicationtest.View;
// c'est la premier activitée mmeme si son nom est connexion car je me suis trompé au début et j'ai pa su comment la changé.

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.model.MyApplication;
import com.example.kenzack.applicationtest.model.Session;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.example.kenzack.applicationtest.service.AuthentificationService;

public class connexion extends AppCompatActivity {
     String Extra_login="user_login",Extra_pass="";
    Button cnx,insc;
    TextView t1,t2;
    EditText login , pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importer_image);
        login=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
       cnx=(Button)findViewById(R.id.button2);
        Extra_login=login.getText().toString();
        Extra_pass=pass.getText().toString();
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthentificationService authentificationService = new AuthentificationService();
                String erreurs = authentificationService.authentifier(Extra_login,Extra_pass);
                if(erreurs.isEmpty()) {
                    Session session = new Session();
                    Utilisateur u = new Utilisateur();
                    u.setLogin(Extra_login);
                    session.setUtilisateur(u);
                    MyApplication myApplication = (MyApplication)connexion.this.getApplication();
                    myApplication.setSession(session);
                    // inetent permet de passer d'une activité a une autre
                    Intent intent = new Intent(connexion.this,acceuil.class);
                    //putExtra permet d'inserer qlq chose dans l autre activité , ici je vous qu'eele se rappelle du login
                    intent.putExtra(Extra_login,login.getText().toString());
                    startActivity(intent);
                }
                else {
                    //afficher un message d'erreur
                }

            }
        });
        insc=(Button)findViewById(R.id.button3);
        insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(connexion.this,formulaire.class);
                startActivity(intent);
            }
        });

        pass=(EditText)findViewById(R.id.editText2);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);

    }
}
