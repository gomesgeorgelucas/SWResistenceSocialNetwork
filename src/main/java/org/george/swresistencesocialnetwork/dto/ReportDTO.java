package org.george.swresistencesocialnetwork.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    Long suspectId;
    Long accuserId;
}
