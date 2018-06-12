package de.Cypix.Lobby.CMD;

import de.Cypix.Lobby.Files.Var;
import de.Cypix.Lobby.Manager.LocationManager;
import de.Cypix.Lobby.Manager.SpawnManager;
import de.Cypix.Lobby.navigator.Item;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("Lobby.set") || p.getName().equalsIgnoreCase("Cypix")){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("spawn")){
                        SpawnManager.setSpawn(p.getLocation());
                        p.sendMessage(Var.pr+"Du hast den Spawn §aErfolgreich §7gesetzt !");
                        return true;
                    }
                    Item item = Item.valueOfServerGroup(args[0]);
                    if(item != null){
                        LocationManager.saveLocation(p.getLocation(), item.getServerGroup());
                    }else{
                        p.sendMessage("Du hast den Namen der Server Gruppe Falsch angegeben !");
                    }

                }
                p.sendMessage("§cUse: /set <spawn/Servergruppe>");
            }else p.sendMessage(Var.noperm);
        }


        return false;
    }
}
