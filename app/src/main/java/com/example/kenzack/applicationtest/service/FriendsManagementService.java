package com.example.kenzack.applicationtest.service;

import com.example.kenzack.applicationtest.model.FriendShipState;
import com.example.kenzack.applicationtest.model.Friendship;
import com.example.kenzack.applicationtest.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziouiz on 22/05/16.
 */
public class FriendsManagementService extends AbstractService{
    public void addFriend(Utilisateur utilisateur_envoie,Utilisateur utilisateur_recoit) {
        try {
            Friendship friendship = new Friendship();
            friendship.setUtilisateur_envoie(utilisateur_envoie);
            friendship.setUtilisateur_recoit(utilisateur_recoit);
            friendship.setFriendshipState(FriendShipState.SENT);
            friendShipDao.create(friendship);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void acceptFriendShip(Utilisateur utilisateur_envoie,Utilisateur utilisateur_recoit) {
        try {
            Friendship friendship = friendShipDao.queryBuilder().where().eq("utilisateur_envoie_id", utilisateur_envoie.getId()).and().eq("utilisateur_recoit_id", utilisateur_recoit.getId()).queryForFirst();
            if( friendship != null) {
                friendship.setFriendshipState(FriendShipState.ACCEPTED);
                friendShipDao.update(friendship);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void refuseFriendShip(Utilisateur utilisateur_envoie,Utilisateur utilisateur_recoit) {
        try {
            Friendship friendship = friendShipDao.queryBuilder().where().eq("utilisateur_envoie_id", utilisateur_envoie.getId()).and().eq("utilisateur_recoit_id", utilisateur_recoit.getId()).queryForFirst();
            if( friendship != null) {
                friendship.setFriendshipState(FriendShipState.REFUSED);
                friendShipDao.update(friendship);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Utilisateur> getFriends(Utilisateur utilisateur) {
        List<Utilisateur> friends = new ArrayList<Utilisateur>();
        try {
            List<Friendship> friendships = friendShipDao.queryBuilder().where().eq("utilisateur_envoie_id", utilisateur.getId()).or().eq("utilisateur_recoit_id", utilisateur.getId()).and().eq("friendshipState",FriendShipState.ACCEPTED).query();
            for(Friendship friendship : friendships) {
                if (friendship.getUtilisateur_envoie().getLogin() != utilisateur.getLogin()) {
                    friends.add(friendship.getUtilisateur_envoie());
                }
                else
                    friends.add(friendship.getUtilisateur_recoit());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return friends;
        }
    }
    public String[] getInvitations(Utilisateur utilisateur) {
        String[] logins = null;
        try {
            List<Friendship> friendships = friendShipDao.queryBuilder().where().eq("utilisateur_recoit_id", utilisateur.getId()).and().eq("friendshipState",FriendShipState.SENT).query();
            logins = new String[friendships.size()];
            int i = 0;
            for(Friendship friendship : friendships) {
                logins[i++] = friendship.getUtilisateur_envoie().getLogin();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return logins;
        }
    }
}
