package org.george.swresistencesocialnetwork.dto;

import lombok.*;
import org.george.swresistencesocialnetwork.exception.enums.BaseEnum;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RebelDTO {
    @NotBlank(message = "Name is mandatory")
    String name;
    @Min(0)
    @NotNull
    Integer age;
    @NotBlank(message = "Gender is mandatory")
    String gender;
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

    @NotNull
    Collection<@Valid ItemDTO> inventory;
}
