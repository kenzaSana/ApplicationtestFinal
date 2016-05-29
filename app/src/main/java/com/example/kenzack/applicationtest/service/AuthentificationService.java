package com.example.kenzack.applicationtest.service;

import com.example.kenzack.applicationtest.Utils.HashUtils;
import com.example.kenzack.applicationtest.model.Utilisateur;

import java.util.List;

/**
 * Created by KenZack on 11/05/2016.
 */
public class AuthentificationService extends AbstractService {

    public AuthentificationService(){
        super();
    }

    public Utilisateur authentifier(String login,String passwd) {
        Utilisateur utilisateur = null;
        try {
            String hashedPasswd = HashUtils.MD5(passwd);
            utilisateur = utilisateurDao.queryBuilder().where().eq("login", login).and().eq("hashedPasswd", hashedPasswd).queryForFirst();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            return utilisateur;
        }
    }
}
