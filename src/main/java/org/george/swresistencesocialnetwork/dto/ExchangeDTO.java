package org.george.swresistencesocialnetwork.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO {
    Long fistRebelId;
    Collection<ItemDTO> firstRebelItems;
    Long secondRebelId;
    Collection<ItemDTO> secondRebelItems;
}
