package org.george.swresistencesocialnetwork.utils;

import org.george.swresistencesocialnetwork.exception.enums.BaseEnum;
import org.george.swresistencesocialnetwork.model.RebelModel;

import java.util.Collections;

public class RebelUtil {

    public static RebelModel createRebelModel() {
        return RebelModel.builder()
                .id(null)
                .name("RebelTest")
                .age(18)
                .gender("Unknown")
                .latitude(0.0)
                .longitude(0.0)
                .base(BaseEnum.ATOLLON)
                .inventory(Collections.emptyList())
                .build();
    }

    public static RebelModel createRebelModel(Long id) {
        return RebelModel.builder()
                .id(id)
                .name("RebelTest")
                .age(18)
                .gender("Unknown")
                .latitude(0.0)
                .longitude(0.0)
                .base(BaseEnum.ATOLLON)
                .inventory(InventoryUtil.createInventory())
                .build();
    }

}
