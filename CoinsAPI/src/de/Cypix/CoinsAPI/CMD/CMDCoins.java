package de.Cypix.CoinsAPI.CMD;

import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.Cypix.CoinsAPI.MYSQL.Manager;

public class CMDCoins implements CommandExecutor {
    private static String pr = "§7[§bCoins§7] ";


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            Coins.sendToBungeeCord(p, "data", p.getUniqueId().toString());
            if(p.hasPermission("CoinsAPI")) {
                if(args.length >= 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        Manager m = new Manager(target);
                        if(args.length == 1) {
                            if(p.getName().equalsIgnoreCase(target.getName())) {
                                p.sendMessage(pr+"Du hast §b"+Coins.getCoins(p)+" Coins.");
                                 return true;
                            }
                            p.sendMessage(pr+"Der Spieler "+target.getName()+ " hat "+m.getCoins()+ " Coins");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("set")) {
                            m.setCoins(Integer.valueOf(args[2]));
                            p.sendMessage("§cDie Coins wurden auf "+args[2]+ " gesetzt!");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("add")) {
                            Coins.addCoins(target, Integer.valueOf(args[2]));
                            p.sendMessage("§cEs wurden "+args[2]+" Coins hinzugefügt!");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("remove")) {
                            Coins.removeCoins(target, Integer.valueOf(args[2]));
                            p.sendMessage("§cEs wurden "+args[2]+" Coins removed!");
                            return true;
                        }
                        p.sendMessage("§cBitte benutze /coins <player> set/remove/add <coins>");
                    }else {
                        if(args.length == 1){
                            p.sendMessage(pr+"Der Spieler "+args[0]+" hat "+Manager.getCoinsPlayer(args[0]));
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("set")) {
                            Manager.setCoinsPlayer(args[0], Integer.valueOf(args[2]));
                            p.sendMessage("§cDie Coins wurden auf "+args[2]+ " gesetzt!");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("add")) {
                            Coins.addCoins(args[0], Integer.valueOf(args[2]));
                            p.sendMessage("§cEs wurden "+args[2]+" Coins hinzugefügt!");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("remove")) {
                            Coins.removeCoins(target, Integer.valueOf(args[2]));
                            p.sendMessage("§cEs wurden "+args[2]+" Coins removed !");
                            return true;
                        }


                        if(args[0].equalsIgnoreCase("help")) {
                            p.sendMessage("§cBitte benutze /coins <player> set/remove/add <coins>");
                            return true;
                        }

                        p.sendMessage("§cERROR (Bitte Melden)");
                    }
                }else if(args.length == 0) {
                    p.sendMessage(pr+"Du hast "+Coins.getCoins(p)+ " Coins !");
                }else {
                    p.sendMessage("§cBitte benutze /coins <player> set/remove/add <coins>");

                }
            }else {
                p.sendMessage(pr+"Du hast §b"+Coins.getCoins(p)+" Coins.");
            }
        }
        return false;
    }

    public static void updatePoints(Player p, int points ) {
        JsonObject pointsObject = new JsonObject();
        pointsObject.addProperty( "coins", points );

        LabyModPlugin.getInstance().sendServerMessage( p, "Coins", pointsObject );
    }



}
