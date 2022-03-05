package org.george.swresistencesocialnetwork.controller;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.converts.ItemModelDTO;
import org.george.swresistencesocialnetwork.dto.*;
import org.george.swresistencesocialnetwork.model.ItemModel;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.george.swresistencesocialnetwork.service.ItemService;
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
    ItemService itemService;

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

    @PostMapping("/trade")
    public ResponseEntity<TradeDTO> trade(@RequestBody TradeDTO tradeDTO) {
        if (tryTrade(tradeDTO)) {
            return new ResponseEntity<>(tradeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(tradeDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Checks if both lists have the same amount of points total.
     * @param tradeDTO DTO containing transaction data.
     * @return true or false
     */
    private boolean tryTrade(TradeDTO tradeDTO) {
        if (
                isBlocked(tradeDTO.getFirstRebelId())
                        || isBlocked(tradeDTO.getSecondRebelId())
        ) {
            return false;
        }

        if (
                isListInvalid(tradeDTO.getFirstRebelId(), tradeDTO.getFirstRebelItems())
                        || isListInvalid(tradeDTO.getSecondRebelId(), tradeDTO.getSecondRebelItems())
        ) {
            return false;
        }

        if (!getPoints(tradeDTO.getFirstRebelItems()).equals(getPoints(tradeDTO.getSecondRebelItems()))) {
             return false;
        }

        doTrade(tradeDTO);

        return true;
    }

    private void doTrade(TradeDTO tradeDTO) {
        rebelService.removeItems(tradeDTO.getFirstRebelId(), tradeDTO.getFirstRebelItems());


      //  itemService.remove(rebelService.getRebel(tradeDTO.getFirstRebelId()), tradeDTO.getFirstRebelItems());
   //    itemService.remove(rebelService.getRebel(tradeDTO.getSecondRebelId()), tradeDTO.getSecondRebelItems());

//        rebelService.updateInventory(
//                tradeDTO.getFirstRebelId(),
//                tradeDTO.getFirstRebelItems(),
//                tradeDTO.getSecondRebelItems()
//        );
//        rebelService.updateInventory(
//                tradeDTO.getSecondRebelId(),
//                tradeDTO.getSecondRebelItems(),
//                tradeDTO.getFirstRebelItems()
//        );

//        rebelService.removeItems(tradeDTO.getFirstRebelId(), tradeDTO.getFirstRebelItems());
//        rebelService.removeItems(tradeDTO.getSecondRebelId(), tradeDTO.getSecondRebelItems());
//
//        rebelService.updateInventory(tradeDTO.getFirstRebelId(), tradeDTO.getSecondRebelItems());
//        rebelService.updateInventory(tradeDTO.getSecondRebelId(), tradeDTO.getFirstRebelItems());
    }

    /**
     * Checks for traitor status
     * @param id suspect id
     * @return true or false
     */
    private boolean isBlocked(Long id) {
        return reportService.isBlocked(id);
    }

    /**
     * Calculates the amount of points in the list.
     * @param itemsList List of items (enum)
     * @return Integer value representing total amount.
     */
    private Integer getPoints(Collection<ItemDTO> itemsList) {
        Integer points = 0;

        for (ItemDTO item: itemsList) {
            points += item.getItemType().getValue();
        }

        return points;
    }

    /**
     * Checks if all items in the list are available in the rebels inventory
     * @param id rebels id
     * @param itemsList List of items to be exchanged
     * @return true or false
     */
    private boolean isListInvalid(Long id, Collection<ItemDTO> itemsList) {
        RebelModel rebel = rebelService.getRebel(id);
        return !rebel.getInventory().containsAll(new ItemModelDTO().convert(rebel, itemsList));
    }



}
