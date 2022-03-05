package org.george.swresistencesocialnetwork.model;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemModel)) return false;
        ItemModel itemModel = (ItemModel) o;
        return this.hashCode() == itemModel.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType);
    }
}
