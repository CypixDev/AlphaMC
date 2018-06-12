package de.Cypix.Test.Regionen;

public enum RegionType{

    NOHIT("nohit"),
    NOBUILD("nobuild"),
    NODAMAGE("nodamage"),
    NOENTER("noenter"),;


    private String name;

    RegionType(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static RegionType valueof(String name){
        for(RegionType region : RegionType.values()){
            if(region.getName().equalsIgnoreCase(name)) return region;
        }
        return null;
    }

}
