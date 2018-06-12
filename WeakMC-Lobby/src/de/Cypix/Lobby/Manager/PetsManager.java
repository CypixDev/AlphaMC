package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.MYSQL.MYSQL;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PetsManager {

    public static HashMap<Player, Entity> list = new HashMap<Player, Entity>();

    public PetsManager(){}

    public void createPet(Player player ,EntityType type){
        Entity entity = (Entity)player.getWorld().spawnEntity(player.getLocation(), type);
        entity.setCustomName(player.getName()+"'s Pet");
        entity.setCustomNameVisible(true);
        list.put(player, entity);
    }


    public void followPlayer(Creature creature, Player player, double Speed){
        Location location = player.getLocation();


        Random rnd = new Random();
        int zufall = rnd.nextInt(6);
        switch(zufall){
            case 0:
                location.add(1.5,0,1.5);
                break;
            case 1:
                location.add(0,0,1.5);
                break;
            case 2:
                location.add(1.5,0,0);
                break;
            case 3:
                location.subtract(1.5,0,1.5);
                break;
            case 4:
                location.subtract(0,0,1.5);
                break;
            case 5:
                location.subtract(1.5,0,0);
                break;
        }


        if(location.distanceSquared(creature.getLocation()) > 100){
            if(!player.isOnGround()){
                return;
            }
            creature.teleport(location);
        }else{
            ((CraftCreature)creature).getHandle().getNavigation().a(location.getX(),location.getY(),location.getZ(),Speed);
        }
    }

    public static Inventory getInv(Player p){


        return null;
    }
    public static boolean hasBought(Player p, String name){
        ResultSet rs = MYSQL.getResult("SELECT * FROM pets WHERE UUID='"+p.getUniqueId()+"' AND "+name+"='true'");
        List<String> pets = new ArrayList<>();
        try{
            return rs.next();
        }catch(SQLException e1){

        }
        return false;
    }

}
