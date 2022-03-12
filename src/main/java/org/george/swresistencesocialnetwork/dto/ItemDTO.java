package org.george.swresistencesocialnetwork.dto;

import lombok.*;
import org.george.swresistencesocialnetwork.enums.ItemTypeEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @Min(0)
    @Max(3)
    @NotNull
    ItemTypeEnum itemType;
}
