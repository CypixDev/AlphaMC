package de.Cypix.Lobby.MYSQL;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MYSQLPlayer {

    Player p;

    public MYSQLPlayer(Player p){
        this.p = p;
    }

    public void saveSpawnLocation(){
        MYSQL.update("UPDATE loc SET World='"+p.getLocation().getWorld().getName()+"' WHERE UUID='"+p.getUniqueId()+"'");
        MYSQL.update("UPDATE loc SET X='"+p.getLocation().getX()+"' WHERE UUID='"+p.getUniqueId()+"'");
        MYSQL.update("UPDATE loc SET Y='"+p.getLocation().getY()+"' WHERE UUID='"+p.getUniqueId()+"'");
        MYSQL.update("UPDATE loc SET Z='"+p.getLocation().getZ()+"' WHERE UUID='"+p.getUniqueId()+"'");
        MYSQL.update("UPDATE loc SET Yaw='"+p.getLocation().getYaw()+"' WHERE UUID='"+p.getUniqueId()+"'");
        MYSQL.update("UPDATE loc SET Pitch='"+p.getLocation().getPitch()+"' WHERE UUID='"+p.getUniqueId()+"'");
        Bukkit.getConsoleSender().sendMessage("loc von "+p.getName()+" gespeichert !");
    }

    public boolean isinlist(String list){
        ResultSet set = MYSQL.getResult("SELECT * FROM "+list+" WHERE UUID='"+p.getUniqueId()+"'");
        try{
            return set.next();
        }catch (SQLException e5){
            e5.printStackTrace();
        }
        return false;
    }


    public Location getLocation() {
        if(isinlist("loc")) {
            ResultSet rs = MYSQL.getResult("SELECT * FROM loc WHERE UUID='"+p.getUniqueId()+"'");
            try {
                while(rs.next()) {
                    World world = Bukkit.getWorld(rs.getString("World"));
                    double x = rs.getDouble("X");
                    double y = rs.getDouble("Y");
                    double z = rs.getDouble("Z");
                    float yaw = rs.getFloat("Yaw");
                    float pitch = rs.getFloat("Pitch");
                    Location loc = new Location(world, x, y, z, yaw, pitch);
                    return loc;
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return null;
    }



}
