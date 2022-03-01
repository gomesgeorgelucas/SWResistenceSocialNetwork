package org.george.swresistencesocialnetwork.repository;

import org.george.swresistencesocialnetwork.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {}
