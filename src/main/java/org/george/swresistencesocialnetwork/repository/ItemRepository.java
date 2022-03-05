package org.george.swresistencesocialnetwork.repository;

import org.george.swresistencesocialnetwork.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    Collection<ItemModel> findAllByRebelId(Long id);
}
