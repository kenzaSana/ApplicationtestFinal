package com.example.kenzack.applicationtest;

/**
 * Created by KenZack on 11/05/2016.
 */

import com.example.kenzack.applicationtest.model.Droit;
import com.example.kenzack.applicationtest.model.Image;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.junit.Test;

import java.sql.SQLException;


public class InitDatabaseDev {
    private final String DB_NAME = "DEV";
    private final String LOGIN_MYSQL ="root";
    private final String PASSWORD_MYSQL ="";
    private final String IP_MYSQL = "localhost";

    @Test
    public void init() throws SQLException {
        String url = "jdbc:mysql://"+IP_MYSQL+":3306/";
        ConnectionSource connectionSource = new JdbcConnectionSource(url,LOGIN_MYSQL,PASSWORD_MYSQL);
        Dao<Utilisateur, Integer> utilisateurDao = DaoManager.createDao(connectionSource,Utilisateur.class);
        utilisateurDao.executeRaw("drop database DEV;");
        utilisateurDao.executeRaw("create database DEV;");
        utilisateurDao.executeRaw("use "+DB_NAME+";");
        TableUtils.createTable(connectionSource, Utilisateur.class);
        TableUtils.createTable(connectionSource, Image.class);
        TableUtils.createTable(connectionSource, Droit.class);
        connectionSource.close();
    }
}
