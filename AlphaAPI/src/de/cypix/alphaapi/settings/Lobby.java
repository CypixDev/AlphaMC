package de.cypix.alphaapi.settings;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Lobby {

    private StatsPlayer sp;
    private boolean loaded;
    private HashMap<LobbyOption, Integer> optionlist;

    public Lobby(StatsPlayer sp){
        this.sp = sp;
        loaded = false;
        optionlist = new HashMap<>();
    }

    private void loadLobbyOptionsIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM Options_Lobby WHERE UUID='"+sp.getPlayer().getUniqueId()+"';");
            try{
                while(rs.next()){
                    optionlist.put(LobbyOption.valueOf(rs.getInt("Options")), rs.getInt("Value"));
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    public void addOption(LobbyOption lo){
        loadLobbyOptionsIfNotLoaded();
        optionlist.put(lo, -1);
        Mysql.update("INSERT INTO Options_Lobby(UUID, Options, Value) VALUES ('"+sp.getPlayer().getUniqueId()+"', '"+lo.getId()+"', '100')");
    }
    public void addOption(LobbyOption lo, int value){
        loadLobbyOptionsIfNotLoaded();
        optionlist.put(lo, value);
        Mysql.update("INSERT INTO Options_Lobby(UUID, Options, Value) VALUES ('"+sp.getPlayer().getUniqueId()+"', '"+lo.getId()+"', '"+value+"')");
    }
    public void removeOption(LobbyOption lo){
        loadLobbyOptionsIfNotLoaded();
        optionlist.remove(lo);
        Mysql.update("DELETE FROM Options_Lobby WHERE UUID='"+sp.getPlayer().getUniqueId()+"' AND Options='"+lo.getId()+"';");
    }

    public HashMap<LobbyOption, Integer> getOptionlist() {
        loadLobbyOptionsIfNotLoaded();
        return optionlist;
    }
}
