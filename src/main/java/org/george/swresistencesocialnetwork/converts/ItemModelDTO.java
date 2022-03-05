package org.george.swresistencesocialnetwork.converts;

import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;

import java.util.ArrayList;
import java.util.Collection;

public class ItemModelDTO {
    public Collection<ItemModel> convert(RebelModel rebel, Collection<ItemDTO> itemDTOList) {
        Collection<ItemModel> convertedList = new ArrayList<>();
        for (ItemDTO item :
                itemDTOList) {
            convertedList.add(ItemModel.builder()
                    .id(rebel.getId())
                    .itemType(item.getItemType())
                    .rebel(rebel)
                    .build());
        }
        return convertedList;
    }
}
