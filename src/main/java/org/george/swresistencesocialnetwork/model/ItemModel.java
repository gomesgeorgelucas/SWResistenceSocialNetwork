package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    ItemTypeEnum itemType;
    @ManyToOne
    @JoinColumn(name = "rebel_id")
    RebelModel rebel;

}
