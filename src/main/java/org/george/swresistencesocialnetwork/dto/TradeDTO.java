package org.george.swresistencesocialnetwork.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {
    Long firstRebelId;
    Collection<ItemDTO> firstRebelItems;
    Long secondRebelId;
    Collection<ItemDTO> secondRebelItems;
}
