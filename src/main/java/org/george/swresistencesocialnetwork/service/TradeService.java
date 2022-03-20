package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.dto.ItemDTO;
import org.george.swresistencesocialnetwork.dto.TradeDTO;
import org.george.swresistencesocialnetwork.mappers.ItemMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

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
    private boolean tryTrade(TradeDTO tradeDTO) {
        if (Objects.equals(tradeDTO.getFirstRebelId(), tradeDTO.getSecondRebelId())) {
            return false;
        }

        try {
            if (
                    reportService.isBlocked(tradeDTO.getFirstRebelId())
                            || reportService.isBlocked(tradeDTO.getSecondRebelId())
            ) {
                return false;
            }
        } catch (Exception ignored) {}

        try {
            if (
                    isListInvalid(tradeDTO.getFirstRebelId(), tradeDTO.getFirstRebelItems())
                            || isListInvalid(tradeDTO.getSecondRebelId(), tradeDTO.getSecondRebelItems())
            ) {
                return false;
            }
        } catch (Exception ignored) {}

        try {
            if (!getPoints(tradeDTO.getFirstRebelItems()).equals(getPoints(tradeDTO.getSecondRebelItems()))) {
                return false;
            }
        } catch (Exception ignored) {}

        try {
            return doTrade(tradeDTO);
        } catch (Exception ignored) {}

        return false;
    }

    private boolean doTrade(TradeDTO tradeDTO) {
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
        return !rebelService.getRebel(id).getInventory().containsAll(ItemMapper.itemDTOToItemTypeEnum(itemsList));
    }
}
