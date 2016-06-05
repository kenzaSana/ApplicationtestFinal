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

    @Test
    public void addition_isCorrect() {
        String nom="ab.de.png";
        String[] array = nom.split("\\.");
        String s = array[array.length-1];
        String ss;
    }
}