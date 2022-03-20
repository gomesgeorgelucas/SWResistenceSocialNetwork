package org.george.swresistencesocialnetwork.Utils;

import org.george.swresistencesocialnetwork.enums.BaseEnum;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;
import org.george.swresistencesocialnetwork.model.RebelModel;

import java.util.Arrays;
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
                .inventory(Collections.emptyList())
                .build();
    }

}
