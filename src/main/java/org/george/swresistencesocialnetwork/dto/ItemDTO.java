package org.george.swresistencesocialnetwork.dto;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    ItemTypeEnum itemType;
}
