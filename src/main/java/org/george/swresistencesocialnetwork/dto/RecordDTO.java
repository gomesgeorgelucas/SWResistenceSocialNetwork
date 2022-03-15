package org.george.swresistencesocialnetwork.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    Double traitorsPercentage;
    Double rebelsPercentage;
    Collection<Double> averageResourcesPerRebel;
    Long lostPointsDueToTraitors;
}
