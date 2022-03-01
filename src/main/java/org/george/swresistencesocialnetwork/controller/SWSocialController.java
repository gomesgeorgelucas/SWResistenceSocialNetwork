package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.dto.UpdateLocationDTO;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.service.RebelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/swapi")
@AllArgsConstructor
public class SWSocialController {

    RebelService rebelService;

    @PostMapping("/addRebel")
    public ResponseEntity<RebelDTO> addRebel(@RequestBody RebelDTO rebelDTO) {
        rebelService.addRebel(rebelDTO);
        return new ResponseEntity<>(rebelDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<RebelDTO> updateLocation(@PathVariable Long id, @RequestBody UpdateLocationDTO updateLocationDTO) {
        RebelModel rebel = rebelService.updateLocation(id, updateLocationDTO);
        RebelDTO rebelDTO = RebelDTO.builder()
                .name(rebel.getName())
                .age(rebel.getAge())
                .gender(rebel.getGender())
                .latitude(rebel.getLatitude())
                .longitude(rebel.getLongitude())
                .base(rebel.getBase())
                .build();

        ArrayList<ItemDTO> items = new ArrayList<>();
        for (ItemModel item : rebel.getInventory()) {
            items.add(new ItemDTO(item.getItemType()));
        }
        rebelDTO.setInventory(items);

        return new ResponseEntity<>(rebelDTO, HttpStatus.OK);
    }

}
