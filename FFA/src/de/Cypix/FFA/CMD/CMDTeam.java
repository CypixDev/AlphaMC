package de.Cypix.FFA.CMD;

import de.Cypix.FFA.Manager.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTeam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if(target != null) {
                    if (args[0].equalsIgnoreCase("invite")) {
                        TeamManager.sendInvite(p, target);
                        target.sendMessage("Du wurdest von " + p.getDisplayName() + " in ein Team eigeladen !");
                        target.sendMessage("Clicke hier um beizutreten ");
                        p.sendMessage("Du hast " + target.getDisplayName() + " eine einladung geschickt !");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("accept")){
                        if(TeamManager.isInvited(p, target)) {
                            p.sendMessage("Der Spieler " + target.getDisplayName() + " hat dass Team betreten !");
                            target.sendMessage("Du hast dass Team betreten !");
                            TeamManager.setTeam(target, p);
                        }else p.sendMessage("Du hast keine einladung von diesem Spieler erhalten !");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("deny")){
                        if(TeamManager.isInvited(p, target)){
                            TeamManager.denyInvite(p);
                            p.sendMessage("Du hast die anfrage für ein Team abgelehnt");
                            target.sendMessage("Der Spieler "+p.getDisplayName()+" hat deine anfrage zum Team abgelehnt");
                            return true;
                        }
                    }
                }else{
                    p.sendMessage("Du kannst nur online Spieler einladen !");
                    return true;
                }
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("leave")){
                    if(TeamManager.isinTeam(p)){
                        TeamManager.getTeamMember(p).sendMessage("Dass Team wurde aufgelöst !");
                        p.sendMessage("Du hast dass Team verlassen !");
                        TeamManager.leaveTeam(p);
                        return true;
                    }
                }
                if(args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("info")){
                    if(TeamManager.isinTeam(p)){
                        p.sendMessage("Du bist in einem Team mit "+TeamManager.getTeamMember(p).getDisplayName()+" !");
                    }else{
                        p.sendMessage("Du bist in keinem Team !");
                    }
                    return true;
                }
            }



            p.sendMessage("/team invite <Player>");
            p.sendMessage("/team accept <Player>");
            p.sendMessage("/team deny <Player>");
            p.sendMessage("/team list/info");
            p.sendMessage("/team leave");

        }

        return false;
    }
}
