package org.george.swresistencesocialnetwork.repository;

import org.george.swresistencesocialnetwork.model.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long> {
    Collection<ReportModel> findBySuspectId(Long suspectId);
}
