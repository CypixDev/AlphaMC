package de.cypix.alphaapi.settings;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Generally {

    private StatsPlayer sp;
    private boolean loaded;
    private HashMap<GenerallyOptions, String> optionlist;

    public Generally(StatsPlayer sp){
        this.sp = sp;
        this.loaded = false;
        this.optionlist = new HashMap<>();
    }

    private void loadGenerallyOptionsIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM Options_Generally WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
            try{
                while(rs.next()){
                    optionlist.put(GenerallyOptions.valueOf(rs.getInt("Options")), rs.getString("Value"));
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }


    public void addOption(GenerallyOptions lo){
        loadGenerallyOptionsIfNotLoaded();
        optionlist.put(lo, "null");
        Mysql.update("INSERT INTO Options_Generally(UUID, Options, Value) VALUES ('"+sp.getPlayer().getUniqueId()+"', '"+lo.getId()+"', 'null')");
    }
    public void addOption(GenerallyOptions lo, String value){
        loadGenerallyOptionsIfNotLoaded();
        optionlist.put(lo, value);
        Mysql.update("INSERT INTO Options_Generally(UUID, Options, Value) VALUES ('"+sp.getPlayer().getUniqueId()+"', '"+lo.getId()+"', '"+value+"')");
    }
    public void removeOption(GenerallyOptions lo){
        loadGenerallyOptionsIfNotLoaded();
        optionlist.remove(lo);
        Mysql.update("DELETE FROM Options_Generally WHERE UUID='"+sp.getPlayer().getUniqueId()+"' AND Options='"+lo.getId()+"';");
    }

    public HashMap<GenerallyOptions, String> getOptionlist() {
        loadGenerallyOptionsIfNotLoaded();
        return optionlist;
    }
}
