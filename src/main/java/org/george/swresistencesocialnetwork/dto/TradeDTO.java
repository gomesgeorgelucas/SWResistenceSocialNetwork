package org.george.swresistencesocialnetwork.dto;

import lombok.*;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {
    @NotNull
    @Min(0)
    Long firstRebelId;
    @NotEmpty(message = "Input firstRebelItems cannot be empty.")
    Collection<@Valid ItemDTO> firstRebelItems;
    @NotNull
    @Min(0)
    Long secondRebelId;
    @NotNull
    @NotEmpty(message = "Input secondRebelItems cannot be empty.")
    Collection<@Valid ItemDTO> secondRebelItems;
}
