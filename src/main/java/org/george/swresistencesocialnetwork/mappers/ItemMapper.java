package org.george.swresistencesocialnetwork.mappers;

import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.model.enums.ItemTypeEnum;

import java.util.ArrayList;
import java.util.Collection;

public class ItemMapper {

    public static Collection<ItemTypeEnum> itemDTOToItemTypeEnum(Collection<ItemDTO> itemDTOList) {
        Collection<ItemTypeEnum> itemTypeEnums = new ArrayList<>();
        for (ItemDTO item: itemDTOList) {
            itemTypeEnums.add(item.getItemType());
        }

        return itemTypeEnums;
    }

    public static Collection<ItemDTO> itemTypeEnumToItemDTO(Collection<ItemTypeEnum> itemTypeEnumList) {
        Collection<ItemDTO> itemDTOCollection = new ArrayList<>();

        for (ItemTypeEnum item: itemTypeEnumList) {
            itemDTOCollection.add(ItemDTO.builder()
                    .itemType(item)
                    .build());
        }

        return itemDTOCollection;
    }
}
