package de.cypix.tsbot.ts;

import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.cypix.tsbot.mc.main.Main;

public class SupportEvents {

    //final static int clientid = TeamSpeakBot.getTs3Api().whoAmI().getId();

    public static void loadEvents() {
        TeamSpeakBot.getTs3Api().registerAllEvents();
        //Main.getInstance().getLogger().info("Die Events werden geladen.....");
        TeamSpeakBot.getTs3Api().addTS3Listeners(new TS3Listener() {
            @Override
            public void onTextMessage(TextMessageEvent e) {

            }

            @Override
            public void onClientJoin(ClientJoinEvent e) {

            }

            @Override
            public void onClientLeave(ClientLeaveEvent e) {

            }

            @Override
            public void onServerEdit(ServerEditedEvent e) {

            }

            @Override
            public void onChannelEdit(ChannelEditedEvent e) {

            }

            @Override
            public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent e) {

            }

            @Override
            public void onClientMoved(ClientMovedEvent e) {
                //Main.getInstance().getLogger().info("ok !");
                int teammembers = 0;
                if(e.getTargetChannelId() == 10894481){
                    TeamSpeakBot.getTs3Api().sendPrivateMessage(e.getClientId(), "[color=red]Du hast den Support betreten.");
                    for(Client c : TeamSpeakBot.getTs3Api().getClients()){
                            for(int i = 0;i < c.getServerGroups().length;i++){
                                if(TeamSpeakBot.getGroups().contains(c.getServerGroups()[i])){
                                    teammembers++;
                                    TeamSpeakBot.getTs3Api().sendPrivateMessage(c.getId(), "Jemand ist im Support !");
                                }else{
                                    Main.getInstance().getLogger().info(c.getNickname()+" hat keine Erlaubniss !");
                                }
                            }
                            TeamSpeakBot.getTs3Api().sendPrivateMessage(TeamSpeakBot.getTs3Api().getClientByUId(e.getInvokerUniqueId()).getId(), "Es wurden "+teammembers+" Team mitglieder benachrichtigt !");
                        }
                    }
            }

            @Override
            public void onChannelCreate(ChannelCreateEvent e) {

            }

            @Override
            public void onChannelDeleted(ChannelDeletedEvent e) {

            }

            @Override
            public void onChannelMoved(ChannelMovedEvent e) {

            }

            @Override
            public void onChannelPasswordChanged(ChannelPasswordChangedEvent e) {

            }

            @Override
            public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent e) {

            }
        });
        //Main.getInstance().getLogger().info("Die Events wurden geladen.....");
    }

}
