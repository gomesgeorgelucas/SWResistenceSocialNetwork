package org.george.swresistencesocialnetwork.utils;

import org.george.swresistencesocialnetwork.dto.LocationDTO;
import org.george.swresistencesocialnetwork.enums.BaseEnum;

public class LocationUtil {
    public static LocationDTO createLocation() {
        return LocationDTO.builder()
                .latitude(5.0)
                .longitude(-10.0)
                .base(BaseEnum.CRAIT)
                .build();
    }
}
