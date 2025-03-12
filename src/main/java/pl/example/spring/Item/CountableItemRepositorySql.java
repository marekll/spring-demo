package pl.example.spring.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountableItemRepositorySql extends CountableItemRepository, JpaRepository<CountableItem, Long> {
}
