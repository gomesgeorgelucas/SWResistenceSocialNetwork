package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.dto.LocationDTO;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class RebelService {
    @Autowired
    final RebelRepository rebelRepository;

    public RebelModel addRebel(RebelModel rebel) {
        if (rebel == null) {
            throw new InvalidRequestException();
        } else if (rebel.getId() != null) {
            throw new InvalidRequestException();
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

    public RebelModel updateInventory(Long id, Collection<ItemDTO> itemsToRemove, Collection<ItemDTO> itemsToAdd) {
        RebelModel rebel = rebelRepository.getById(id);

        Collection<ItemTypeEnum> itemTypesToRemove = new ArrayList<>();
        Collection<ItemTypeEnum> itemTypesToAdd = new ArrayList<>();

        for (ItemDTO item : itemsToRemove) {
            itemTypesToRemove.add(item.getItemType());
        }

        for (ItemTypeEnum item : itemTypesToRemove) {
            rebel.getInventory().remove(item);
        }

        rebelRepository.save(rebel);

        for (ItemDTO item : itemsToAdd) {
            itemTypesToAdd.add(item.getItemType());
        }

        rebel.getInventory().addAll(itemTypesToAdd);

        return rebelRepository.save(rebel);
    }
}
