package com.example.kenzack.applicationtest;

/**
 * Created by KenZack on 11/05/2016.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import com.example.kenzack.applicationtest.Utils.ImageUtils;
import com.example.kenzack.applicationtest.model.Droit;
import com.example.kenzack.applicationtest.model.Image;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestUtilisateurImage {
    private ConnectionSource connectionSource;
    private Dao<Utilisateur, Integer> utilisateurDao;
    private Dao<Image, Integer> imageDao;
    private Dao<Droit, Integer> droitDao;
    private String IMAGE_TEST_PATH;
    private Properties properties = new Properties();
    private String fichier_proprietes = "C:\\Users\\KenZack\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\kenzack\\myapplication\\test.properties";

    @Before
    public void initialiser() throws Exception{
        //Chargement des parametres depuis test.properties
        properties = new Properties();
        InputStream in = new FileInputStream(fichier_proprietes);
        properties.load(in);
        String IP_MYSQL_TEST = properties.getProperty("IP_MYSQL_TEST");
        String DB_TEST = properties.getProperty("DB_TEST");
        String LOGIN_MYSQL_TEST = properties.getProperty("LOGIN_MYSQL_TEST");
        String PASSWORD_MYSQL_TEST = properties.getProperty("PASSWORD_MYSQL_TEST");
        String url = "jdbc:mysql://"+IP_MYSQL_TEST+":3306/"+DB_TEST;
        connectionSource = new JdbcConnectionSource(url,LOGIN_MYSQL_TEST,PASSWORD_MYSQL_TEST); //construire cnx
        imageDao = DaoManager.createDao(connectionSource, Image.class);
        utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
        utilisateurDao.executeRaw("drop database if exists test ;");
        utilisateurDao.executeRaw("create database if not exists test;");
        utilisateurDao.executeRaw("use test;");
        TableUtils.createTable(connectionSource,Utilisateur.class);
        TableUtils.createTable(connectionSource,Image.class);
    }
    @After
    public void closeConnection() throws Exception{
        connectionSource.close();
    }

    @Test
    public void testCreateUserWithTwoImages() throws Exception{
        Utilisateur u1 = new Utilisateur();
        u1.setLogin("u1");
        Image i1 = new Image();
        i1.setNom("i1");
        String IMAGE_TEST_PATH = properties.getProperty("IMAGE_TEST_PATH");
        byte[] image = ImageUtils.getByteArrayFromImage(IMAGE_TEST_PATH);
        i1.setImage(image);
        i1.setProprietaire(u1);
        Image i2 = new Image();
        i2.setNom("i2");
        i2.setProprietaire(u1);
        utilisateurDao.create(u1);
        imageDao.create(i1);
        imageDao.create(i2);
        Utilisateur fromDb = utilisateurDao.queryForId(u1.getId());
        // assertEquals(fromDb.getImagesCree().size(),2);
    }
}