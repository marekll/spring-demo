package pl.example.spring.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UncountableItemRepositorySql extends UncountableItemRepository, JpaRepository<UncountableItem, Long> {
}
