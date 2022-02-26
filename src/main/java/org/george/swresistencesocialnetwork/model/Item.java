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
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    ItemTypeEnum itemType;
    @ManyToOne
    Rebel rebel;

}
