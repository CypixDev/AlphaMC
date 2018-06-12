package de.cypix.buildffa.api;

import org.bukkit.Bukkit;

public class Reflections {
    private Reflections(){}

    private static /*final*/ String ver;
    private static final String nms = "net.minecraft.server.";

    static{
        try{
            String pkg = Bukkit.getServer().getClass().getPackage().getName();
            ver = pkg.substring(pkg.lastIndexOf(".") + 1);
        }catch(Exception e){
            System.out.println("Couldn't get Bukkit version..");
        }
    }

    public static String version(){
        return ver;
    }
    public static String versionNMS(){
        return nms + version();
    }
    public static Class<?> getNMSClass(String name){
        return getClass(versionNMS() + '.' + name);
    }

    public static Class<?> getClass(String name){
        try{
            return Class.forName(name);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}
