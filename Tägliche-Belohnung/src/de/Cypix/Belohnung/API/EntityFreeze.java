package de.Cypix.Belohnung.API;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;

import org.bukkit.entity.Entity;

import net.minecraft.server.v1_8_R3.NBTTagEnd;

public class EntityFreeze {

    private static final LinkedList<Entity> FROZEN_ENTITIES = new LinkedList<Entity>();
    private static Class<?> clazz;

    static{
        try{
            clazz = Reflections.getNMSClass("NBTTagCompound");


        }catch(Exception e){
            clazz = NBTTagEnd.class;
        }
    }

    public static void freeze(Entity e){
        try{
            // Getting all needed fields and methods

            Method handle = e.getClass().getMethod("getHandle");
            Object nmsEnt = handle.invoke(e);
            Method methC = nmsEnt.getClass().getMethod("c", clazz);
            Method methf = nmsEnt.getClass().getMethod("f", clazz);


            //Create the Compound
            Object compound = null;
            for(Constructor<?> consts : clazz.getConstructors()){
                if(consts.getParameterCount() == 0) compound = consts.newInstance();
            }

            //Add the Compound to the Entity
            methC.invoke(nmsEnt, compound);

            //Set the Compounds values
            Method setByte = compound.getClass().getMethod("setByte", String.class, Byte.TYPE);
            setByte.invoke(compound, "NoAI", (byte) 1);

            //Register the changes
            methf.invoke(nmsEnt, compound);

            FROZEN_ENTITIES.add(e);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //public static List<Entity> getFrozenEntites(){
    //	return ImmutableList.copyOf(FROZEN_ENTITIES);
    //}
}