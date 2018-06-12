package de.Cypix.CityBuild.CMD.Gamamode;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDGm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.gm")){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("0")){
                        if(p.getGameMode().equals(GameMode.SURVIVAL)){
                            p.sendMessage(Var.pr+"Du bist bereits in §bGame-Modus 0");
                        }else{
                            p.sendMessage(Var.pr+"Du bist jetzt im §bGame-Modus 0");
                            p.setGameMode(GameMode.SURVIVAL);
                        }
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("1")){
                        if(p.getGameMode().equals(GameMode.CREATIVE)){
                            p.sendMessage(Var.pr+"Du bist bereits in §bGame-Modus 1");
                        }else{
                            p.sendMessage(Var.pr+"Du bist jetzt im §bGame-Modus 1");
                            p.setGameMode(GameMode.CREATIVE);
                        }
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("2")){
                        if(p.getGameMode().equals(GameMode.ADVENTURE)){
                            p.sendMessage(Var.pr+"Du bist bereits in §bGame-Modus 2");
                        }else{
                            p.sendMessage(Var.pr+"Du bist jetzt im §bGame-Modus 2");
                            p.setGameMode(GameMode.ADVENTURE);
                        }
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("3")){
                        if(p.getGameMode().equals(GameMode.SPECTATOR)){
                            p.sendMessage(Var.pr+"Du bist bereits in §bGame-Modus 3");
                        }else{
                            p.sendMessage(Var.pr+"Du bist jetzt im §bGame-Modus 3");
                            p.setGameMode(GameMode.SPECTATOR);
                        }
                        return true;
                    }
                }
                if(args.length == 2){
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target != null){
                        if(args[0].equalsIgnoreCase("0")){
                            if(target.getGameMode().equals(GameMode.SURVIVAL)){
                                p.sendMessage(Var.pr+"§7Der §bSpieler §7ist bereits im §bGame-Mode 0");
                            }else{
                                p.sendMessage(Var.pr+"§7Der §bGame-Modus §7von §b"+target.getName()+" §7wurde auf §b0 §7gesetzt!");
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage(Var.pr+"Dein §bGame-Modus §7wurde von §b"+p.getName()+" auf §b0 §7gesetzt!");
                            }
                            return true;
                        }
                        if(args[0].equalsIgnoreCase("1")){
                            if(target.getGameMode().equals(GameMode.CREATIVE)){
                                p.sendMessage("§7Der §bSpieler §7ist bereits im §bGame-Mode 1");
                            }else{
                                p.sendMessage(Var.pr+"§7Der §bGame-Modus §7von §b"+target.getName()+" §7wurde auf §b1 §7gesetzt!");
                                target.setGameMode(GameMode.CREATIVE);
                                target.sendMessage(Var.pr+"Dein §bGame-Modus §7wurde von §b"+p.getName()+" auf §b1 §7gesetzt!");
                            }
                            return true;
                        }
                        if(args[0].equalsIgnoreCase("2")){
                            if(target.getGameMode().equals(GameMode.ADVENTURE)){
                                p.sendMessage(Var.pr+"§7Der §bSpieler §7ist bereits im §bGame-Mode 0");
                            }else{
                                p.sendMessage(Var.pr+"§7Der §bGame-Modus §7von §b"+target.getName()+" §7wurde auf §b2 §7gesetzt!");
                                target.setGameMode(GameMode.ADVENTURE);
                                target.sendMessage(Var.pr+"Dein §bGame-Modus §7wurde von §b"+p.getName()+" auf §b2 §7gesetzt!");
                            }
                            return true;
                        }
                        if(args[0].equalsIgnoreCase("3")){
                            if(target.getGameMode().equals(GameMode.SPECTATOR)){
                                p.sendMessage(Var.pr+"§7Der §bSpieler §7ist bereits im §bGame-Mode 3");
                            }else{
                                p.sendMessage(Var.pr+"§7Der §bGame-Modus §7von §b"+target.getName()+" §7wurde auf §b3 §7gesetzt!");
                                target.setGameMode(GameMode.SPECTATOR);
                                target.sendMessage(Var.pr+"Dein §bGame-Modus §7wurde von §b"+p.getName()+" auf §b3 §7gesetzt!");
                            }
                            return true;
                        }

                    }else{
                        p.sendMessage(Var.offline);
                    }
                }
                p.sendMessage(Var.pr+"§7Bitte benutze: §b/gm <0/1/2/3) <User>");
            }else{
                p.sendMessage(Var.noperm);
            }
        }
        return false;
    }
}
