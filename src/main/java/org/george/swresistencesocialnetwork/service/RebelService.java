package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.dto.LocationDTO;
import org.george.swresistencesocialnetwork.enums.BaseEnum;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;
import org.george.swresistencesocialnetwork.exception.InvalidListException;
import org.george.swresistencesocialnetwork.exception.InvalidRequestException;
import org.george.swresistencesocialnetwork.exception.RebelNotFoundException;
import org.george.swresistencesocialnetwork.mappers.ItemMapper;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RebelService {
    @Autowired
    final RebelRepository rebelRepository;

    public RebelModel addRebel(RebelModel rebel) {
        if (rebel == null || rebel.getId() != null || rebel.getInventory() == null) {
            throw new InvalidRequestException();
        }

        return rebelRepository.save(rebel);
    }

    public RebelModel updateLocation(Long id, LocationDTO updateLocationDTO) {
        if (
                updateLocationDTO == null
                || id == null || id <= 0
                || updateLocationDTO.getBase() == null
                || updateLocationDTO.getLatitude() > 90.00 || updateLocationDTO.getLatitude() < -90.00
                || updateLocationDTO.getLongitude() > 180.00 || updateLocationDTO.getLongitude() < -180.00
        ) {
            throw new InvalidRequestException();
        }

        Optional<RebelModel> optional = rebelRepository.findById(id);

        if (!optional.isPresent()) {
            throw new RebelNotFoundException();
        }

        RebelModel rebel = optional.get();
        rebel.setLatitude(updateLocationDTO.getLatitude());
        rebel.setLongitude(updateLocationDTO.getLongitude());
        rebel.setBase(updateLocationDTO.getBase());

        return rebelRepository.save(rebel);
    }

    public RebelModel getRebel(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidRequestException();
        }

        Optional<RebelModel> optional = rebelRepository.findById(id);

        if (!optional.isPresent()) {
            throw new RebelNotFoundException();
        }

        return rebelRepository.getById(id);
    }

    public boolean updateInventory(Long id, Collection<ItemTypeEnum> itemsToRemove, Collection<ItemTypeEnum> itemsToAdd) {
        RebelModel rebel = getRebel(id);

        if (itemsToRemove == null || itemsToAdd == null) {
            throw new InvalidRequestException();
        }

        if (itemsToRemove.isEmpty() || itemsToAdd.isEmpty()) {
            throw new InvalidListException();
        }

        for (ItemTypeEnum item : itemsToRemove) {
            rebel.getInventory().remove(item);
        }

        rebelRepository.save(rebel);

        rebel.getInventory().addAll(itemsToAdd);

        rebelRepository.save(rebel);

        return true;
    }
}
