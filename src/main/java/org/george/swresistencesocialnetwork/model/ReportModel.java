package org.george.swresistencesocialnetwork.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    Long suspectId;

    @ElementCollection
    Set<Long> accusers;
}
