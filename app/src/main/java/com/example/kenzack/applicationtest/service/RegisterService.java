package com.example.kenzack.applicationtest.service;

/**
 * Created by KenZack on 11/05/2016.
 */

import com.example.kenzack.applicationtest.Utils.HashUtils;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.j256.ormlite.dao.DaoManager;


public class RegisterService extends AbstractService{

    public RegisterService(){
        super();
    }

    public void register(String login,String email,String passwd){
        try{
            // insértion des données
            utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setLogin(login);
            utilisateur.setEmail(email);
            String hadshedPasswd = HashUtils.MD5(passwd);
            utilisateur.setHashedPasswd(hadshedPasswd);
            utilisateurDao.create(utilisateur);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
