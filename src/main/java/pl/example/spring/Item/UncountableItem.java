package pl.example.spring.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "uncountable_item")
public class UncountableItem extends Item {
}