package org.george.swresistencesocialnetwork.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    @NotNull
    @Min(0)
    Long suspectId;
    @Min(0)
    @NotNull
    Long accuserId;
}
