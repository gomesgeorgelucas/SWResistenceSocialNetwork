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
    @NotNull
    String name;
    @NotNull
    Integer age;
    @NotNull
    String gender;
    @NotNull
    Double latitude;
    @NotNull
    Double longitude;

    @NotNull
    BaseEnum base;
    @ElementCollection
    @NotNull
    Collection<ItemTypeEnum> inventory;
}
