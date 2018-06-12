package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.MYSQL.MYSQL;
import de.Cypix.Lobby.inventar.Extras.Boots;
import de.Cypix.Lobby.inventar.Extras.Heads;
import de.Cypix.Lobby.inventar.Extras.Pets;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveItemManager {

    public static void setItem(Player p){
        if(isinlist(p)) {
            try {
                ResultSet rs = MYSQL.getResult("SELECT * FROM save WHERE UUID='" + p.getUniqueId() + "'");

                if (rs.getInt("Head") != 0) {
                    p.getInventory().setHelmet(Heads.valueof(rs.getInt("Head")).getItemStack());
                }
                if (rs.getInt("Boots") != 0) {
                    p.getInventory().setBoots(Boots.valueof(rs.getInt("Boots")).getItemStack());
                }
                if (rs.getInt("Pet") != 0) {
                    Pets pet = Pets.valueof(rs.getInt("Pet"));
                    new PetsManager().createPet(p, pet.getType());
                }
                /*if (rs.getString("GadGet") != null) {
                    //gadgets setzten !
                }*/
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            MYSQL.update("DELETE FROM save WHERE UUID='" + p.getUniqueId() + "'");
        }

    }

    public static void saveItems(Player p){
        int id = 0;
        int helm = 0;
        int boots = 0;
        if(PetsManager.list.containsKey(p)){
            Pets pet = Pets.valueof(PetsManager.list.get(p));
            id = pet.getID();
        }
        if(!p.getInventory().getBoots().equals(Material.AIR)){
            helm = Boots.valueof(p.getInventory().getBoots().getItemMeta().getDisplayName()).getID();
        }
        if(!p.getInventory().getHelmet().equals(Material.AIR)){
            boots = Heads.valueof(p.getInventory().getHelmet().getItemMeta().getDisplayName()).getID();
        }

        MYSQL.update("INSERT INTO save(UUID, Head, Boots, Pet, GadGet) VALUES('"+p.getUniqueId()+"', '"+helm+"', '"+boots+"', '"+id+"', '0')");
    }

    public static boolean isinlist(Player p){
        ResultSet rs = MYSQL.getResult("SELECT * FROM save WHERE UUID='"+p.getUniqueId()+"'");

        try{
            return rs.next();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return false;
    }


}
