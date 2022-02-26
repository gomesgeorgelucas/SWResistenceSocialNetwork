package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    ItemTypeEnum itemType;
    @ManyToOne
    RebelModel rebel;

}
