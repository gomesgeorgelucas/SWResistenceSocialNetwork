package org.george.swresistencesocialnetwork.utils;

import org.george.swresistencesocialnetwork.model.enums.ItemTypeEnum;

import java.util.Arrays;
import java.util.Collection;;

public class InventoryUtil {
    public static Collection<ItemTypeEnum> createInventory() {
        return Arrays.asList(ItemTypeEnum.AMMO, ItemTypeEnum.FOOD, ItemTypeEnum.AMMO, ItemTypeEnum.WATER, ItemTypeEnum.WEAPON);
    }
}
