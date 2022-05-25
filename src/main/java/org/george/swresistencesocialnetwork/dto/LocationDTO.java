package org.george.swresistencesocialnetwork.dto;

import lombok.*;
import org.george.swresistencesocialnetwork.model.enums.BaseEnum;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    @DecimalMin(value = "-90.00")
    @DecimalMax(value = "90.00")
    @NotNull
    Double latitude;
    @DecimalMin(value = "-180.00")
    @DecimalMax(value = "180.00")
    @NotNull
    Double longitude;
    @NotNull
    BaseEnum base;
}
