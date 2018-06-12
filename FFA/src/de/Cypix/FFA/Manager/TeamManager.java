package de.Cypix.FFA.Manager;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    private static HashMap<Player, Player> list = new HashMap<Player, Player>();
    private static HashMap<Player, Player> invite = new HashMap<Player, Player>();

    public static boolean isinTeam(Player p){
        if(list.containsKey(p)) return true;
        return false;
    }

    public static void sendInvite(Player p1, Player p2){
        invite.put(p2, p1);
    }

    public static Player getInvites(Player p){
        return invite.get(p);
    }

    public static boolean isInvited(Player p ,Player target){
        if(invite.containsKey(p)){
            if(invite.get(p) == target)return true;
        }
        return false;
    }

    public static void setTeam(Player p1, Player p2){
        if(list.containsKey(p1)){
            list.remove(p1);
        }
        if(list.containsKey(p2)){
            list.remove(p2);
        }
        list.put(p1, p2);
        list.put(p2, p1);
    }
    public static Player getTeamMember(Player p){
        return list.get(p);
    }
    public static void denyInvite(Player p){
        if(invite.containsKey(p)){
            invite.remove(p);
        }
    }

    public static HashMap<Player, Player> getList(){
        return list;
    }
    public static void leaveTeam(Player p){
        if(list.containsKey(p)){
            list.remove(list.get(p));
            list.remove(p);
        }
    }

    public static HashMap<Player, Player> getInvite(){
        return invite;
    }


}
