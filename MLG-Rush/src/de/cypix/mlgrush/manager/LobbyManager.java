package de.cypix.mlgrush.manager;

import de.cypix.mlgrush.main.Main;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LobbyManager {

    private static File file = new File(Main.getInstance().getDataFolder(), "Lobby.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setSpawnLocation(Location loc) {
        System.out.println("Spawn wurde getzt !");
        saveLocation("Location", loc);
    }
    public static void teleportToSpawn(Player p){
        p.teleport(loadLocation("Location"));
    }
    public static Location getSpawnLocation(){
        return loadLocation("Location");
    }
    public static void setItems(Player p){
        p.getInventory().clear();
        p.getInventory().setHeldItemSlot(0);
        p.getInventory().setItem(0, Item(Material.STICK, "Herrausfrdern (Schlagen)", Arrays.asList("Wenn du andere Spieler Schlägst", "vordest du sie zum Kapf herraus !")));
        p.getInventory().setItem(2, Item(Material.BLAZE_ROD, "Spieler verstecken", Arrays.asList("Rechts Click um Spieler zu verstecken !")));
        p.getInventory().setItem(4, Item(Material.CHEST, "Spiele", Arrays.asList("Hier sihst du alle Spiele die ingame sind !")));
        p.getInventory().setItem(8, Item(Material.COMMAND, "Inventar einstellen", Arrays.asList("Hier kannst du die anordnung deines Inventars dauerhaft ändern")));
    }
    public static ItemStack Item(Material mat, String displayname, List<String> lore){
        ItemStack i = new ItemStack(mat);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack Item(Material mat, String displayname, List<String> lore, int amount){
        ItemStack i = new ItemStack(mat);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }

    private static void saveLocation(String path, Location loc){
        cfg.set(path+".World", loc.getWorld().getName());
        cfg.set(path+".X", loc.getX());
        cfg.set(path+".Y", loc.getY());
        cfg.set(path+".Z", loc.getZ());
        cfg.set(path+".Yaw", loc.getY());
        cfg.set(path+".Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Location loadLocation(String path){

        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");

        Location loc = new Location(world, x, y ,z ,yaw, pitch);

        return loc;
    }


}
