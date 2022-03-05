package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.converts.ItemModelDTO;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.dto.LocationDTO;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class RebelService {
    final RebelRepository rebelRepository;

    public RebelModel addRebel(RebelDTO rebelDTO) {
        RebelModel rebel = new RebelModel();
        rebel.setName(rebelDTO.getName());
        rebel.setAge(rebelDTO.getAge());
        rebel.setGender(rebelDTO.getGender());
        rebel.setLatitude(rebelDTO.getLatitude());
        rebel.setLongitude(rebelDTO.getLongitude());
        rebel.setBase(rebelDTO.getBase());

        rebel.setInventory(new ArrayList<>());
        for (ItemDTO itemDTO : rebelDTO.getInventory()) {
            rebel.getInventory().add(
                    ItemModel.builder()
                            .itemType(itemDTO.getItemType())
                            .rebel(rebel).build()
            );
        }

        return rebelRepository.save(rebel);
    }

    public RebelModel updateLocation(Long id, LocationDTO updateLocationDTO) {
        RebelModel rebel = rebelRepository.findById(id).get();
        rebel.setLatitude(updateLocationDTO.getLatitude());
        rebel.setLongitude(updateLocationDTO.getLongitude());
        rebel.setBase(updateLocationDTO.getBase());
        return rebelRepository.save(rebel);
    }

    public RebelModel getRebel(Long id) {
        return rebelRepository.findById(id).get();
    }

    public RebelModel updateInventory(Long id, Collection<ItemDTO> toAddList) {
        RebelModel rebel = rebelRepository.findById(id).get();

        for (ItemDTO itemDTO : toAddList) {
            rebel.getInventory().add(
                    ItemModel.builder()
                            .itemType(itemDTO.getItemType())
                            .rebel(rebel).build()
            );
        }

        return rebel;
    }

    public RebelModel updateInventory(Long id, Collection<ItemDTO> toRemoveList, Collection<ItemDTO> toAddList) {
        RebelModel rebel = rebelRepository.findById(id).get();

//        if (toRemoveList != null) {
//            Collection<ItemModel> itemsRebel = rebel.getInventory();
//            Collection<ItemModel> itemsToRemove = new ItemModelDTO().convert(rebel, toRemoveList);
//            for (ItemModel item : itemsToRemove) {
//                if (itemsRebel.contains(item)) {
//                    itemsRebel.remove(item);
//                }
//            }
//            rebel.setInventory(itemsRebel);
//
//        }

        for (ItemDTO itemDTO : toAddList) {
            rebel.getInventory().add(
                    ItemModel.builder()
                            .itemType(itemDTO.getItemType())
                            .rebel(rebel).build()
            );
        }

        rebelRepository.save(rebel);

        return rebel;
    }

    public RebelModel removeItems(Long id, Collection<ItemDTO> list) {
        RebelModel rebel = rebelRepository.findById(id).get();
        Collection<ItemModel> itemsRebel = rebel.getInventory();
        Collection<ItemModel> itemsToRemove = new ItemModelDTO().convert(rebel, list);
        for (ItemModel item : itemsToRemove) {
            itemsRebel.remove(item);
        }

        rebel.setInventory(new ArrayList<>());

        return rebel;
    }
}
