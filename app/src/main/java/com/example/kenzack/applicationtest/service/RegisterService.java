package com.example.kenzack.applicationtest.service;

/**
 * Created by KenZack on 11/05/2016.
 */

import android.util.Log;


import com.example.kenzack.applicationtest.Utils.HashUtils;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;


public class RegisterService {
    private final String DB_NAME = "DEV";
    private final String LOGIN_MYSQL = "kniza";
    private final String PASSWORD_MYSQL = "kenza";
    private final String IP_MYSQL = "192.168.1.14";
    private ConnectionSource connectionSource;
    private Dao<Utilisateur, Integer> utilisateurDao;

    public RegisterService(){
        try {
            //conx avec la base donné
            String url = "jdbc:mysql://" + IP_MYSQL + ":3306/"+DB_NAME;
            connectionSource = new JdbcConnectionSource(url, LOGIN_MYSQL, PASSWORD_MYSQL);
            utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
        }
        catch (Exception e){
            Log.i("erreur",e.getMessage());
            e.printStackTrace();
        }
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
