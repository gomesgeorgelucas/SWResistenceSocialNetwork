package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.*;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.service.RebelService;
import org.george.swresistencesocialnetwork.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/swapi")
@AllArgsConstructor
public class SWSocialController {

    RebelService rebelService;
    ReportService reportService;

    @PostMapping("/addRebel")
    public ResponseEntity<RebelDTO> addRebel(@RequestBody RebelDTO rebelDTO) {
        rebelService.addRebel(rebelDTO);
        return new ResponseEntity<>(rebelDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<RebelDTO> updateLocation(@PathVariable Long id, @RequestBody LocationDTO updateLocationDTO) {
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

    @PostMapping("/reportSuspect")
    public ResponseEntity<ReportDTO> reportSuspect(@RequestBody ReportDTO reportDTO) {
        RebelModel suspect = rebelService.getRebel(reportDTO.getSuspectId());
        RebelModel accuser = rebelService.getRebel(reportDTO.getAccuserId());
        if (suspect.getId() == accuser.getId()) {
            return new ResponseEntity<>(reportDTO, HttpStatus.BAD_REQUEST);
        }
        reportService.report(suspect, accuser);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

    @PostMapping("/exchange")
    public ResponseEntity<ExchangeDTO> exchangeItems(@RequestBody ExchangeDTO exchangeDTO) {
        return null;
    }

    /**
     * Checks if both lists have the same amount of points total.
     * @param exchangeDTO DTO containing transaction data.
     * @return true or false
     */
    private boolean tryExchange(ExchangeDTO exchangeDTO) {
        if (
                isBlocked(exchangeDTO.getFistRebelId())
                        || isBlocked(exchangeDTO.getSecondRebelId())
        ) {
            return false;
        }

        if (
                isListInvalid(exchangeDTO.getFistRebelId(), exchangeDTO.getFirstRebelItems())
                        || isListInvalid(exchangeDTO.getSecondRebelId(), exchangeDTO.getSecondRebelItems())
        ) {
            return false;
        }

        if (!getPoints(exchangeDTO.getFirstRebelItems()).equals(getPoints(exchangeDTO.getSecondRebelItems()))) {
             return false;
        }

        rebelService.removeItems(exchangeDTO.getFistRebelId(), exchangeDTO.getFirstRebelItems());
        rebelService.removeItems(exchangeDTO.getSecondRebelId(), exchangeDTO.getSecondRebelItems());

        rebelService.updateInventory(exchangeDTO.getFistRebelId(), exchangeDTO.getSecondRebelItems());
        rebelService.updateInventory(exchangeDTO.getSecondRebelId(), exchangeDTO.getFirstRebelItems());

        return true;
    }

    /**
     * Checks for traitor status
     * @param id suspect id
     * @return true or false
     */
    private boolean isBlocked(Long id) {
        return true;
    }

    /**
     * Calculates the amount of points in the list.
     * @param itemsList List of items (enum)
     * @return Integer value representing total amount.
     */
    private Integer getPoints(Collection<ItemDTO> itemsList) {
        return 0;
    }

    /**
     * Checks if all items in the list are available in the rebels inventory
     * @param id rebels id
     * @param itemsList List of items to be exchanged
     * @return true or false
     */
    private boolean isListInvalid(Long id, Collection<ItemDTO> itemsList) {
        return true;

    }



}
