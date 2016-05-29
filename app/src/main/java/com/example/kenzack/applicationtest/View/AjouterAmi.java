package com.example.kenzack.applicationtest.View;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.model.MyApplication;
import com.example.kenzack.applicationtest.model.Session;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.example.kenzack.applicationtest.service.FriendsManagementService;
import com.example.kenzack.applicationtest.service.UtilisateurManagementService;

/**
 * Created by aziouiz on 29/05/16.
 */
public class AjouterAmi extends Activity {
    EditText loginAjoutAmi;
    Button buttonAjouter;
    UtilisateurManagementService uMS = new UtilisateurManagementService();
    FriendsManagementService fMS = new FriendsManagementService();

    private Session getSession(){
        MyApplication myApplication = (MyApplication)this.getApplication();
        return myApplication.getSession();
    }

    private void restartActivity() {
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_ami);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        loginAjoutAmi = (EditText) findViewById(R.id.loginAjoutAmi);
        buttonAjouter = (Button) findViewById(R.id.buttonAjouterAmi);
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginAjoutAmi.getText().toString();
                Utilisateur utilisateur_recoit = uMS.getUtilisateurByLogin(login);
                if(utilisateur_recoit != null) {
                    //l'utilisateur tapé existe et donc on envoie l'invitation
                    Utilisateur utilisateur_envoie = getSession().getUtilisateur();
                    fMS.addFriend(utilisateur_envoie,utilisateur_recoit);
                    restartActivity();
                }
                else {
                    //l'utilisateur n'existe pas -> afficher une erreur ...
                }

            }
        });
    }
}
