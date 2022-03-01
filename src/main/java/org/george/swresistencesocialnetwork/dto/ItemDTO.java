package org.george.swresistencesocialnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    ItemTypeEnum itemType;
}
