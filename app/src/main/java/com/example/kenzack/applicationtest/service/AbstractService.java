package com.example.kenzack.applicationtest.service;

import com.example.kenzack.applicationtest.model.Image;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.util.Properties;

/**
 * Created by KenZack on 17/05/2016.
 */
public class AbstractService {
    private Properties properties = new Properties();
    protected ConnectionSource connectionSource;
    protected Dao<Image, Integer> imageDao;
    protected Dao<Utilisateur, Integer> utilisateurDao;
    private String fichier_proprietes = "C:\\Users\\KenZack\\AndroidStudioProjects\\Applicationtest\\app\\src\\main\\res\\dev.properties";
    public AbstractService(){
        try {
           // InputStream in = new FileInputStream(fichier_proprietes);
            //properties.load(in);
            String IP_MYSQL = "192.168.1.20";
            String DB ="DEV";
            String LOGIN_MYSQL = "kniza";
            String PASSWORD_MYSQL = "kenza";
            String url = "jdbc:mysql://"+IP_MYSQL+":3306/"+DB;
            connectionSource = new JdbcConnectionSource(url, LOGIN_MYSQL, PASSWORD_MYSQL);
            createDaos();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createDaos() throws Exception{
        imageDao = DaoManager.createDao(connectionSource,Image.class);
        utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
    }
}
