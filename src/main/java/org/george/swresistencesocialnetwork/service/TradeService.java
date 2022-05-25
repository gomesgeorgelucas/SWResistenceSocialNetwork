package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.TradeDTO;
import org.george.swresistencesocialnetwork.exception.*;
import org.george.swresistencesocialnetwork.model.enums.ItemTypeEnum;
import org.george.swresistencesocialnetwork.mappers.ItemMapper;
import org.george.swresistencesocialnetwork.model.RebelModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TradeService {
    final RebelService rebelService;
    final ReportService reportService;

    @Transactional
    public String trade(TradeDTO tradeDTO) {
        return tryTrade(tradeDTO) ? "Trade successful." : "Trade failed.";
    }

    /**
     * Checks if both lists have the same amount of points total.
     * @param tradeDTO DTO containing transaction data.
     * @return true or false
     */
    public boolean tryTrade(TradeDTO tradeDTO) {
        if (Objects.equals(tradeDTO.getFirstRebelId(), tradeDTO.getSecondRebelId())) {
            throw new InvalidRequestException();
        }


        if (
                reportService.isBlocked(tradeDTO.getFirstRebelId())
                        || reportService.isBlocked(tradeDTO.getSecondRebelId())
        ) {
            throw new TradeBlockedException();
        }

        if (
                isListInvalid(tradeDTO.getFirstRebelId(), ItemMapper.itemDTOToItemTypeEnum(tradeDTO.getFirstRebelItems()))
                        || isListInvalid(tradeDTO.getSecondRebelId(), ItemMapper.itemDTOToItemTypeEnum(tradeDTO.getSecondRebelItems()))
        ) {
            throw new InvalidListException();
        }


        if (!getPoints(tradeDTO.getFirstRebelItems()).equals(getPoints(tradeDTO.getSecondRebelItems()))) {
            throw new MismatchedTradeException();
        }

        return doTrade(tradeDTO);
    }

    public boolean doTrade(TradeDTO tradeDTO) {
        if (tradeDTO == null) {
            throw new NullPointerException();
        }

        return rebelService.updateInventory(tradeDTO.getFirstRebelId(),
                ItemMapper.itemDTOToItemTypeEnum(tradeDTO.getFirstRebelItems()),
                ItemMapper.itemDTOToItemTypeEnum(tradeDTO.getSecondRebelItems()))
                && rebelService.updateInventory(tradeDTO.getSecondRebelId(),
                ItemMapper.itemDTOToItemTypeEnum(tradeDTO.getSecondRebelItems()),
                ItemMapper.itemDTOToItemTypeEnum(tradeDTO.getFirstRebelItems()));
    }

    /**
     * Calculates the amount of points in the list.
     * @param itemsList List of items (enum)
     * @return Integer value representing total amount.
     */
    public Integer getPoints(Collection<ItemDTO> itemsList) {
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
    public boolean isListInvalid(Long id, Collection<ItemTypeEnum> itemsList) {
        Optional<RebelModel> optional = Optional.ofNullable(rebelService.getRebel(id));

        if (optional.isEmpty()) {
            throw new RebelNotFoundException();
        }

        return !optional.get().getInventory().containsAll(itemsList);
    }
}
