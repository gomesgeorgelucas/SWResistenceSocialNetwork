package org.george.swresistencesocialnetwork.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {
    @Id
    @Column(name = "suspect_id")
    Long suspectId;
    @ElementCollection
    Set<Long> accusers = new HashSet<>();
}
