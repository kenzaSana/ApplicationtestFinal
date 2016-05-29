package com.example.kenzack.applicationtest.service;

import com.example.kenzack.applicationtest.Utils.HashUtils;
import com.example.kenzack.applicationtest.model.Utilisateur;

/**
 * Created by aziouiz on 29/05/16.
 */
public class UtilisateurManagementService extends AbstractService {
    public UtilisateurManagementService(){
        super();
    }
    public Utilisateur getUtilisateurByLogin(final String login) {
        Utilisateur utilisateur = null;
        try {
            utilisateur = utilisateurDao.queryBuilder().where().eq("login", login).queryForFirst();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            return utilisateur;
        }
    }
}
