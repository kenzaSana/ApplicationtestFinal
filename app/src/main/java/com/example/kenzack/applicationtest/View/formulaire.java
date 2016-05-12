package com.example.kenzack.applicationtest.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.service.RegisterService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class formulaire extends AppCompatActivity {
    //TextView login,pass,confirm_pass,email;
    EditText Tlogin,Tpass,Tc,Temail;
    Button cnx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //login=(TextView)findViewById(R.id.textView5);
        //pass=(TextView)findViewById(R.id.textView6);
        //confirm_pass=(TextView)findViewById(R.id.textView7);
        // email=(TextView)findViewById(R.id.textView8);
        Tlogin=(EditText)findViewById(R.id.editText3);
        Tpass=(EditText)findViewById(R.id.editText4);
        Tc=(EditText)findViewById(R.id.editText5);
        Temail=(EditText)findViewById(R.id.editText6);
        cnx=(Button)findViewById(R.id.button);
        final AlertDialog.Builder abd = new AlertDialog.Builder(this);
        abd.setTitle("erreur");
        abd.setPositiveButton("ok", null);
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=Temail.getText().toString();
                final String pass=Tpass.getText().toString();
                final String confirm_pass=Tc.getText().toString();
                final String login=Tlogin.getText().toString();
                //final String pass=Tpass.getText().toString();
                //final String Extra_confirm_pass=Tc.getText().toString();
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(email);

                if( login.equals("")|| email.equals("") || confirm_pass.equals("") || pass.equals("")) {
                    abd.setMessage("Remplissez tout le formulaire");
                    abd.show();
                }else if(!pass.equals(confirm_pass)) {
                    abd.setMessage("Le mot de passe est incorrecte");
                    abd.show();
                }else if(!m.matches()){
                    abd.setMessage("L'adresse email est incorrecte");
                    abd.show();
                } else {
                    RegisterService registerService = new RegisterService();
                    registerService.register(login,email,pass);
                    Toast toast = Toast.makeText(getApplicationContext(), "Inscription réussie !", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(formulaire.this, acceuil.class);
                    intent.putExtra(login, Tlogin.getText().toString());
                    startActivity(intent);

                }

            }
        });

    }

}
