package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.BaseEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder (toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Rebel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    String name;
    Integer age;
    String gender;
    Double latitude;
    Double longitude;
    BaseEnum base;
    @OneToMany
    Collection<Item> inventory;
}
