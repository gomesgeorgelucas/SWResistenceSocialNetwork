package org.george.swresistencesocialnetwork.dto;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.BaseEnum;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    @DecimalMin(value = "-90.00")
    @DecimalMin(value = "90.00")
    @NotNull
    Double latitude;
    @DecimalMin(value = "-180.00")
    @DecimalMin(value = "180.00")
    @NotNull
    Double longitude;
    @Min(0)
    @Max(19)
    @NotEmpty
    BaseEnum base;
}
