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

    public String authentifier(String login,String passwd) {
        StringBuilder errorMessage = new StringBuilder();
        try {
            String hashedPasswd = HashUtils.MD5(passwd);
            List<Utilisateur> utilisateurList = utilisateurDao.queryBuilder().where().eq("login", login).and().eq("hashedPasswd", hashedPasswd).query();
            if(utilisateurList.size() == 0)
                errorMessage.append("Login/Password erroné");
        }
        catch(Exception e) {
            e.printStackTrace();
            errorMessage.append(e.getMessage());
        }
        finally{
            return errorMessage.toString();
        }
    }
}
