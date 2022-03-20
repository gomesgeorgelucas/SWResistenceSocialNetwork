package org.george.swresistencesocialnetwork.mappers;

import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.model.RebelModel;

public class RebelMapper {

    public static RebelModel rebelDTOtoRebelModel(RebelDTO rebelDTO) {
        return RebelModel.builder()
                .name(rebelDTO.getName())
                .age(rebelDTO.getAge())
                .gender(rebelDTO.getGender())
                .latitude(rebelDTO.getLatitude())
                .longitude(rebelDTO.getLongitude())
                .base(rebelDTO.getBase())
                .inventory(ItemMapper.itemDTOToItemTypeEnum(rebelDTO.getInventory()))
                .build();
    }

    public static RebelDTO rebelModelToRebelDTO(RebelModel rebelModel) {
        return RebelDTO.builder()
                .name(rebelModel.getName())
                .age(rebelModel.getAge())
                .gender(rebelModel.getGender())
                .latitude(rebelModel.getLatitude())
                .longitude(rebelModel.getLongitude())
                .base(rebelModel.getBase())
                .inventory(ItemMapper.itemTypeEnumToItemDTO(rebelModel.getInventory()))
                .build();
    }
}
