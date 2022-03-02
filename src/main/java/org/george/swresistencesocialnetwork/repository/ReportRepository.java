package org.george.swresistencesocialnetwork.repository;

import org.george.swresistencesocialnetwork.model.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long> {
}
