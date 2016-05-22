package com.example.kenzack.applicationtest.model;

import android.app.Application;

/**
 * Created by KenZack on 22/05/2016.
 */
public class MyApplication extends Application {
    private Session session = null;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}