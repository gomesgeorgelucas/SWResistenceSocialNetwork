package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.BaseEnum;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RebelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Name is mandatory")
    @NotNull
    String name;
    @Min(0)
    Integer age;
    @NotBlank(message = "Gender is mandatory")
    @NotNull
    String gender;
    @DecimalMin(value = "-90.00")
    @DecimalMin(value = "90.00")
    Double latitude;
    @DecimalMin(value = "-180.00")
    @DecimalMin(value = "180.00")
    Double longitude;
    @Min(0)
    @Max(19)
    BaseEnum base;

    @ElementCollection
    @NotNull
    Collection<ItemTypeEnum> inventory;
}
