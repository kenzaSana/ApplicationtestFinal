package com.example.kenzack.applicationtest;

/**
 * Created by KenZack on 11/05/2016.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import com.example.kenzack.applicationtest.Utils.ImageUtils;
import com.example.kenzack.applicationtest.model.Droit;
import com.example.kenzack.applicationtest.model.FriendShipState;
import com.example.kenzack.applicationtest.model.Friendship;
import com.example.kenzack.applicationtest.model.Image;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.example.kenzack.applicationtest.service.FriendsManagementService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
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
    private final String DB_NAME = "TEST";
    private final String LOGIN_MYSQL ="root";
    private final String PASSWORD_MYSQL ="helix";
    private final String IP_MYSQL = "localhost";



    private ConnectionSource connectionSource;


    protected Dao<Image, Integer> imageDao;
    protected Dao<Utilisateur, Integer> utilisateurDao;
    protected Dao<Friendship, Integer> friendShipDao;

    @Before
    public void initialiser() throws Exception{

        String url = "jdbc:mysql://"+IP_MYSQL+":3306/TEST";
        connectionSource = new JdbcConnectionSource(url,LOGIN_MYSQL,PASSWORD_MYSQL);
        imageDao = DaoManager.createDao(connectionSource, Image.class);
        Dao<Utilisateur, Integer> utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
        utilisateurDao.executeRaw("drop database if exists TEST;");
        utilisateurDao.executeRaw("create database if not exists TEST;");
        utilisateurDao.executeRaw("use TEST;");
        TableUtils.createTable(connectionSource, Utilisateur.class);
        TableUtils.createTable(connectionSource, Image.class);
        TableUtils.createTable(connectionSource, Droit.class);
        TableUtils.createTable(connectionSource, Friendship.class);
        createDaos();
        connectionSource.close();
    }
    @After
    public void closeConnection() throws Exception{
        connectionSource.close();
    }

    @Test
    public void testCreateUserWithTwoImages() throws Exception{
        /*Utilisateur u1 = new Utilisateur();
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
        // assertEquals(fromDb.getImagesCree().size(),2);*/
    }
    @Test
    public void testFriendShip() throws Exception{
        Utilisateur u1 = new Utilisateur();
        u1.setLogin("u1");
        Utilisateur u2 = new Utilisateur();
        u2.setLogin("u2");
        Utilisateur u3 = new Utilisateur();
        u3.setLogin("u3");
        utilisateurDao.create(u1);
        utilisateurDao.create(u2);
        utilisateurDao.create(u3);

        try {
            Friendship friendship = new Friendship();
            friendship.setUtilisateur_envoie(u1);
            friendship.setUtilisateur_recoit(u2);
            friendship.setFriendshipState(FriendShipState.SENT);
            friendShipDao.create(friendship);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FriendsManagementService fms = new FriendsManagementService();
        fms.addFriend(u1,u2);
        fms.addFriend(u1,u3);
        fms.refuseFriendShip(u1,u2);
        fms.acceptFriendShip(u1,u3);
        List<Utilisateur> u1f = fms.getFriends(u1);
        List<Utilisateur> u2f = fms.getFriends(u2);
        List<Utilisateur> u3f = fms.getFriends(u3);
    }

    @Test
    public  void testNonAcceptes(){

    }

    private void createDaos() throws Exception{
        imageDao = DaoManager.createDao(connectionSource,Image.class);
        utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
        friendShipDao = DaoManager.createDao(connectionSource,Friendship.class);
    }
}