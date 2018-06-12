package de.Cypix.Test;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntityEnderCrystal;
import net.minecraft.server.v1_8_R3.EntityGuardian;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityPig;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.MobEffect;
import net.minecraft.server.v1_8_R3.MobEffectList;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class LaserTower {

    Location loc;
    CustomGuardian guardian;
    CustomPig pig;
    CustomEnderCrystal crystal;
    int radius;
    int damage;

    @SuppressWarnings("unchecked")
    public LaserTower(Location location,int radius,int damage) {
        this.loc = location;
        this.radius = radius;
        this.damage = damage;
        register();
    }

    @SuppressWarnings("unchecked")
    public LaserTower() {
        register();
    }

    @SuppressWarnings("unchecked")
    private void register(){
        try {
            Field c = EntityTypes.class.getDeclaredField("c");
            Field f = EntityTypes.class.getDeclaredField("f");
            c.setAccessible(true);
            f.setAccessible(true);
            ((Map<Class<? extends EntityInsentient>, String>) c.get(null)).put(CustomGuardian.class, "Guardian");
            ((Map<Class<? extends EntityInsentient>, Integer>) f.get(null)).put(CustomGuardian.class, 68);
            ((Map<Class<? extends EntityInsentient>, String>) c.get(null)).put(CustomPig.class, "Pig");
            ((Map<Class<? extends EntityInsentient>, Integer>) f.get(null)).put(CustomPig.class, 90);
        } catch (Exception e) {e.printStackTrace();}
    }

    public void setLocation(Location location){
        this.loc = location;
    }

    public void teleport(Location location){
        this.loc = location;
        respawn();
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void respawn(){
        remove();
        spawn();
    }

    public void remove(){
        if(guardian != null){
            World world = ((CraftWorld)loc.getWorld()).getHandle();
            world.removeEntity(guardian);
            world.removeEntity(pig);
            world.removeEntity(crystal);
            guardian = null;
        }
    }

    public void spawn(){
        remove();
        World world = ((CraftWorld)loc.getWorld()).getHandle();
        guardian = new CustomGuardian(world,radius,damage);
        pig = new CustomPig(world);
        crystal = new CustomEnderCrystal(world);
        guardian.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        pig.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        crystal.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        world.addEntity(guardian);
        world.addEntity(pig);
        world.addEntity(crystal);
        pig.getBukkitEntity().setPassenger(guardian.getBukkitEntity());
    }

}


class CustomEnderCrystal extends EntityEnderCrystal {

    public CustomEnderCrystal(World world) {
        super(world);
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return false;
    }

}

class CustomGuardian extends EntityGuardian{

    int radius;
    int damage;

    public CustomGuardian(World world,int radius,int damage) {
        super(world);
        this.radius = radius;
        this.damage = damage;
        addEffect(new MobEffect(MobEffectList.INVISIBILITY.id, Integer.MAX_VALUE, 999));
        setCustomName("laser");
    }

    @Override
    public void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(damage);
        this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(radius);
    }

    @Override
    public void makeSound(String s, float f, float f1) {
        super.makeSound("", 0, 0);
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return super.damageEntity(null,0);
    }


}


class CustomPig extends EntityPig{

    public CustomPig(World world) {
        super(world);
        addEffect(new MobEffect(MobEffectList.INVISIBILITY.id, Integer.MAX_VALUE, 999));
    }

    @Override
    public void g(double d0, double d1, double d2) {
        super.g(0, 0, 0);
    }

    @Override
    public void move(double d0, double d1, double d2) {
        super.move(0,0,0);
    }

    @Override
    public void makeSound(String s, float f, float f1) {
        super.makeSound("", 0, 0);
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return super.damageEntity(null,0);
    }

}