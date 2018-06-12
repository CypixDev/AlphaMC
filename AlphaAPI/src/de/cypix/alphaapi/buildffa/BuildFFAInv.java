package de.cypix.alphaapi.buildffa;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuildFFAInv {

    private StatsPlayer sp;
    private boolean loaded = false;
    private ArrayList<Integer> list;


    public BuildFFAInv(StatsPlayer sp){
        this.sp = sp;
        this.list = new ArrayList<>();
        loadInvIfNotLoaded();
    }

    private void loadInvIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM BuildFFA_Inv WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
            try{
                if(rs.next()){
                    for(int i = 0;i<9;i++){
                        list.add(rs.getInt("Item"+i));
                    }
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    public void saveInv(ArrayList<Integer> list){
        this.list = list;
        ResultSet rs = Mysql.getResult("SELECT * FROM BuildFFA_Inv WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        try {
            if(rs.next()){
                for(int i = 0;i < 9;i++){
                    if(list.get(i) != i){
                        Mysql.update("UPDATE BuildFFA_Inv SET Item0='"+list.get(0)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item1='"+list.get(1)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item2='"+list.get(2)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item3='"+list.get(3)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item4='"+list.get(4)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item5='"+list.get(5)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item6='"+list.get(6)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item7='"+list.get(7)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        Mysql.update("UPDATE BuildFFA_Inv SET Item8='"+list.get(8)+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                        sp.getPlayer().sendMessage("updated !");
                        return;
                    }
                }
                Mysql.update("DELETE FROM BuildFFA_Inv WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
                sp.getPlayer().sendMessage("removed !");

            }else{
                for(int i = 0;i<9;i++){
                    if(i != list.get(i)){
                        Mysql.update("INSERT INTO BuildFFA_Inv(UUID, Item0, Item1, Item2, Item3, Item4, Item5, Item6, Item7, Item8) VALUES ('"+sp.getPlayer().getUniqueId()+"', '"+list.get(0)+"'," +
                                " '"+list.get(1)+"', '"+ list.get(2)+"', '"+list.get(3)+"', '"+list.get(4)+"', '"+list.get(5)+"', '"+list.get(6)+"', '"+list.get(7)+"', '"+list.get(8)+"')");
                        sp.getPlayer().sendMessage("inserted !");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getInventory(){
        return list;
    }

}
