package com.example.kenzack.applicationtest.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by aziouiz on 22/05/16.
 */
public class Friendship {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Utilisateur utilisateur_envoie;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Utilisateur utilisateur_recoit;
    @DatabaseField
    private FriendShipState friendshipState;

    public FriendShipState getFriendshipState() {
        return friendshipState;
    }

    public void setFriendshipState(FriendShipState friendshipState) {
        this.friendshipState = friendshipState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Utilisateur getUtilisateur_envoie() {
        return utilisateur_envoie;
    }

    public void setUtilisateur_envoie(Utilisateur utilisateur_envoie) {
        this.utilisateur_envoie = utilisateur_envoie;
    }

    public Utilisateur getUtilisateur_recoit() {
        return utilisateur_recoit;
    }

    public void setUtilisateur_recoit(Utilisateur utilisateur_recoit) {
        this.utilisateur_recoit = utilisateur_recoit;
    }
}
