package com.example.kenzack.applicationtest.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.model.MyApplication;
import com.example.kenzack.applicationtest.model.Session;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.example.kenzack.applicationtest.service.FriendsManagementService;
import com.example.kenzack.applicationtest.service.UtilisateurManagementService;

import java.util.List;

/**
 * Created by aziouiz on 29/05/16.
 */
public class Invitations extends Activity {
    ListView listView;
    RelativeLayout invitationsView;
    UtilisateurManagementService uMS = new UtilisateurManagementService();
    FriendsManagementService fMS = new FriendsManagementService();
    String[] invitations_array;
    private Session getSession(){
        MyApplication myApplication = (MyApplication)this.getApplication();
        return myApplication.getSession();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        invitationsView = (RelativeLayout) RelativeLayout.inflate(this,R.layout.activity_invitations,null);
        listView =(ListView) invitationsView.findViewById(R.id.listView);
        invitations_array = fMS.getInvitations(getSession().getUtilisateur());
        RowAdapter adapter=new RowAdapter(this,invitations_array);
        listView.setAdapter(adapter);
        Log.i("yes", "getmain");
        setContentView(invitationsView);
    }

    class RowAdapter extends ArrayAdapter<String> {
        Context context;
        public RowAdapter(Context context, String[] logins) {
            super(context,R.layout.ligne_invitations,R.id.textView2,logins);
            this.context = context;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.i("position",position+"");
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ligne_invitations,parent,false);
            Button buttonAccepter =(Button) convertView.findViewById(R.id.buttonAccepter);
            buttonAccepter.setId(position);
            buttonAccepter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String login_utilisateur_envoie = invitations_array[position];
                    Utilisateur utilisateur_envoie = uMS.getUtilisateurByLogin(login_utilisateur_envoie);
                    Utilisateur utilisateur_recoit = getSession().getUtilisateur();
                    fMS.acceptFriendShip(utilisateur_envoie,utilisateur_recoit);
                    restartActivity();
                }
            });

            Button buttonRefuser =(Button) convertView.findViewById(R.id.buttonRefuser);
            buttonRefuser.setId(position);
            buttonRefuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String login_utilisateur_envoie = invitations_array[position];
                    Utilisateur utilisateur_envoie = uMS.getUtilisateurByLogin(login_utilisateur_envoie);
                    Utilisateur utilisateur_recoit = getSession().getUtilisateur();
                    fMS.refuseFriendShip(utilisateur_envoie,utilisateur_recoit);
                    restartActivity();
                }
            });
            return super.getView(position,convertView,parent);
        }
        //redémarrer l'activité pour rafraichir la liste
        private void restartActivity() {
            Invitations.this.finish();
            Invitations.this.startActivity(getIntent());
        }
    }

}
