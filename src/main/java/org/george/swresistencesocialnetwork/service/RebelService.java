package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
