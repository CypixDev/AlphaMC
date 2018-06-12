package de.Cypix.BungeeSystem.Manager;

import de.Cypix.BungeeSystem.MYSQL.MYSQL;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BanManager {

    public static List<ProxiedPlayer> muted = new ArrayList<ProxiedPlayer>();

    public static void ban(ProxiedPlayer name, String uuid ,int Id, int bantype, long seconds, String operator, String reason){
        if(!isinList(name)){

            long end = 0;
            long current = System.currentTimeMillis();
            long millis = seconds*1000;
            end = current+millis;

            MYSQL.update("INSERT INTO Ban (Player, UUID, Id, Type, Time, Operator, Reason) VALUES ('"+name.getName()+"', '"+name.getUniqueId()+"', '"+Id+"', '"+bantype+"', '"+end+"', '"+operator+"', '"+reason+"');");

        }
    }
    public static void ban(String name, String uuid ,int Id, int bantype, long seconds, String operator, String reason){
        if(!isinList(name)){

            long end = 0;
            long current = System.currentTimeMillis();
            long millis = seconds*1000;
            end = current+millis;

            MYSQL.update("INSERT INTO Ban (Player, UUID, Id, Type, Time, Operator, Reason) VALUES ('"+name+"', 'null', '"+Id+"', '"+bantype+"', '"+end+"', '"+operator+"', '"+reason+"');");

        }
    }
    public static void checkBan(UUID uuid, String name){
        if(isinList(name)){
            if(getRemoaningtimeinLong(name) < 0){
                BanManager.unBan(name);
            }
        }
    }


    public static void unBan(String name){
        MYSQL.update("DELETE FROM Ban WHERE Player='"+name+"'");
    }

    public static boolean isinList(ProxiedPlayer p) {
        String uuid = p.getUniqueId().toString();
        ResultSet set = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
        try {
            return set.next();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public static String getOperator(UUID uuid){
        if(isinList(uuid)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
            try{
                while(rs.next()){
                    return rs.getString("Operator");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isinList(UUID uuid) {
        ResultSet set = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
        try {
            return set.next();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public static boolean isinList(String name) {
        ResultSet set = MYSQL.getResult("SELECT * FROM Ban WHERE Player='"+name+"'");
        try {
            return set.next();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public static String getReaso(UUID uuid){
        if(isinList(uuid)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
            try{
                while(rs.next()){
                    return rs.getString("Reason");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getReaso(String name){
        if(isinList(name)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE Player='"+name+"'");
            try{
                while(rs.next()){
                    return rs.getString("Reason");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String getReason(String name){
        String msg = getReaso(name);
        msg = msg.replace("_", " ");
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public static String getReason(UUID uuid){
        String msg = getReaso(uuid);
        msg = msg.replace("_", " ");
        return ChatColor.translateAlternateColorCodes('&', msg);
    }



    public static int getId(UUID uuid){
        if(isinList(uuid)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
            try{
                while(rs.next()){
                    return rs.getInt("Id");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
    public static int getType(UUID uuid){
        if(isinList(uuid)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
            try{
                while(rs.next()){
                    return rs.getInt("Type");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getType(String name){
        if(isinList(name)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE Player='"+name+"'");
            try{
                while(rs.next()){
                    return rs.getInt("Type");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static boolean isBanned(UUID uuid){
        if(getType(uuid) == BanType.TEMPBAN) return true;
        if(getType(uuid) == BanType.PERMA) return true;
        return false;
    }

    public static boolean isBanned(String name){
        if(getType(name) == BanType.TEMPBAN) return true;
        if(getType(name) == BanType.PERMA) return true;
        return false;
    }

    public static boolean isMuted(UUID uuid){
        if(getType(uuid) == BanType.TEMPMUTE) return true;
        if(getType(uuid) == BanType.MUTE) return true;
        return false;
    }

    public static boolean isMuted(String name){
        if(getType(name) == BanType.TEMPMUTE) return true;
        if(getType(name) == BanType.MUTE) return true;
        return false;
    }


    public static boolean isPermabanned(UUID uuid){
        if((getType(uuid) == BanType.PERMA)) return true;
        return false;
    }
    public static boolean isPermabanned(String name){
        if((getType(name) == BanType.PERMA)) return true;
        return false;
    }

    public static String getStart(UUID uuid){
        if(isinList(uuid)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
            try{
                while(rs.next()){
                    return rs.getString("Start");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public static int getbanid(String name){
        if(isinList(name)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE Player='"+name+"'");
            try{
                while(rs.next()){
                    return Integer.valueOf(rs.getString("Id"));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static long getEnd(UUID uuid){
        if(isinList(uuid)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE UUID='"+uuid+"'");
            try{
                while(rs.next()){
                    return rs.getLong("Time");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static long getEnd(String name){
        if(isinList(name)){
            ResultSet rs = MYSQL.getResult("SELECT * FROM Ban WHERE Player='"+name+"'");
            try{
                while(rs.next()){
                    return rs.getLong("Time");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return 999;
    }


    public static long getRemoaningtimeinLong(UUID uuid){

        long current = System.currentTimeMillis();
        long millis = getEnd(uuid) - current;

        return millis;
    }

    public static long getRemoaningtimeinLong(String name){

        long current = System.currentTimeMillis();
        long millis = getEnd(name) - current;
        if(isPermabanned(name)){
            return 99999999;
        }

        return millis;
    }


    public static String getRemainingTime(UUID uuid){

        if(isPermabanned(uuid)) return "PERMANENT";

        long current = System.currentTimeMillis();
        long millis = getEnd(uuid) - current;

        int seconds = 0;
        int minuets = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        int month = 0;
        int years = 0;

        while(millis >= 1000){
            millis-=1000;
            seconds++;
        }
        while(seconds >= 60){
            seconds -=60;
            minuets++;
        }
        while(minuets >= 60){
            minuets -=60;
            hours++;
        }
        while(hours >= 24){
            hours-=24;
            days++;
        }
        while(days >= 7){
            days -=7;
            weeks++;
        }
        while(weeks >= 4){
            weeks-=4;
            month++;
        }
        while(month > 12){
            month -=12;
            years++;
        }

        String msg = "";
        if(years != 0) msg = msg+years+" Jahre | ";
        if(month != 0) msg = msg+month+" Monate | ";
        if(weeks != 0) msg = msg+weeks+" Wochen | ";
        if(days != 0) msg = msg+days+" Tage | ";
        if(hours != 0) msg = msg+hours+" Stunden | ";
        if(minuets != 0) msg = msg+minuets+" Minuten | ";
        if(seconds != 0) msg = msg+seconds+" Sekunden | ";

        if(msg == "")return millis+"";

        return msg;
    }



    public static String getRemainingTime(String name){

        if(isPermabanned(name)) return "PERMANENT";

        long current = System.currentTimeMillis();
        long millis = getEnd(name) - current;

        int seconds = 0;
        int minuets = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        int month = 0;
        int years = 0;

        while(millis >= 1000){
            millis -=1000;
            seconds++;
        }
        while(seconds >= 60){
            seconds -=60;
            minuets++;
        }
        while(minuets >= 60){
            minuets -=60;
            hours++;
        }
        while(hours >= 24){
            hours -=24;
            days++;
        }
        while(days >= 7){
            days -=7;
            weeks++;
        }
        while(weeks >= 4){
            weeks -=4;
            month++;
        }
        while(month >= 12){
            month -=12;
            years++;
        }

        String msg = "";
        if(years != 0){
            if(years == 1)msg = msg+years+" Jahr | ";
            if(years != 1)msg = msg+years+" Jahre | ";
        }
        if(month != 0){
            if(month == 1)msg = msg+month+" Monat | ";
            if(month != 1)msg = msg+month+" Monate | ";
        }
        if(weeks != 0){
            if(weeks == 1)msg = msg+weeks+" Woche | ";
            if(weeks != 1)msg = msg+weeks+" Wochen | ";
        }
        if(days != 0){
            if(days == 1)msg = msg+days+" Tag | ";
            if(days != 1)msg = msg+days+" Tage | ";
        }
        if(hours != 0){
            if(hours == 1)msg = msg+hours+" Stunde | ";
            if(hours != 1)msg = msg+hours+" Stunden | ";
        }
        if(minuets != 0){
            if(minuets == 1)msg = msg+minuets+" Minute | ";
            if(minuets != 1)msg = msg+minuets+" Minuten | ";
        }
        if(seconds != 0){
            if(seconds == 1)msg = msg+seconds+" Sekunde | ";
            if(seconds != 1)msg = msg+seconds+" Sekunden | ";
        }

        if(msg == "")return millis+"";


        return msg;
    }


    public static void unbanId(Integer integer) {
        MYSQL.update("DELETE FROM Ban WHERE Id='"+integer+"'");
    }
}
