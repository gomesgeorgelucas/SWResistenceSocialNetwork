package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.BaseEnum;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

import javax.persistence.*;
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
    String name;
    Integer age;
    String gender;
    Double latitude;
    Double longitude;
    BaseEnum base;

    @ElementCollection
    Collection<ItemTypeEnum> inventory;
}
