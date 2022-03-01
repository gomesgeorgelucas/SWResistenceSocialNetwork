package org.george.swresistencesocialnetwork.repository;

import org.george.swresistencesocialnetwork.model.RebelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends JpaRepository<RebelModel, Long> {}
