package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.BaseEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="rebel")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RebelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rebel_id")
    Long id;
    String name;
    Integer age;
    String gender;
    Double latitude;
    Double longitude;
    BaseEnum base;
    @OneToMany (mappedBy = "rebel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Collection<ItemModel> inventory;
}
