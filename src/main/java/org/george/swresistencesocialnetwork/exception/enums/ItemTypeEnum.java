package org.george.swresistencesocialnetwork.exception.enums;

public enum ItemTypeEnum {
    WEAPON ("Weapon", 4),
    AMMO ("Ammo", 3),
    WATER ("Water", 2),
    FOOD ("Food", 1);

    Integer value;
    String name;

    ItemTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
    public String getName() { return this.name; }
}
