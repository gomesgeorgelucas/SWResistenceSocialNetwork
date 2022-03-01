package org.george.swresistencesocialnetwork.service;

import lombok.AllArgsConstructor;
import org.george.swresistencesocialnetwork.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {
    final ItemRepository itemRepository;
}
