package com.example.kenzack.applicationtest.View;
// c'est la premier activitée mmeme si son nom est connexion car je me suis trompé au début et j'ai pa su comment la changé.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

public class connexion extends Activity {
     String Extra_login="user_login",Extra_pass="";
    Button cnx,insc;
    TextView t1,t2;
    EditText login , pass;


    private void initSession(Utilisateur utilisateur){
        MyApplication myApplication = (MyApplication)this.getApplication();
        Session session = new Session();
        session.setUtilisateur(utilisateur);
        myApplication.setSession(session);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importer_image);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        login=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
       cnx=(Button)findViewById(R.id.button2);

        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Extra_login=login.getText().toString();
                Extra_pass=pass.getText().toString();
                AuthentificationService authentificationService = new AuthentificationService();
                Utilisateur utilisateur = authentificationService.authentifier(Extra_login,Extra_pass);
                if(utilisateur != null) {
                    initSession(utilisateur);
                    // inetent permet de passer d'une activité a une autre
                    Intent intent = new Intent(connexion.this,acceuil.class);
                    //putExtra permet d'inserer qlq chose dans l autre activité , ici je vous qu'eele se rappelle du login
                    intent.putExtra(Extra_login,login.getText().toString());
                    startActivity(intent);
                }
                else {
                    //afficher un message d'erreur (bad login/passwd)
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
