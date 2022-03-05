package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.converts.ItemModelDTO;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class ItemService {
    final ItemRepository itemRepository;

    public void remove(RebelModel rebel, Collection<ItemDTO> list) {
        Collection<ItemModel> inventory = itemRepository.findAllByRebelId(rebel.getId());
        Collection<ItemModel> listToRemove = new ItemModelDTO().convert(rebel, list);
        Collection<ItemModel> toDelete = new ArrayList<>();

        for (ItemModel item : inventory) {
            if (listToRemove.contains(item)) {
                toDelete.add(item);
            }
        }

        for (ItemModel item : toDelete) {
            itemRepository.deleteById(item.getId());
        }


    }
}
