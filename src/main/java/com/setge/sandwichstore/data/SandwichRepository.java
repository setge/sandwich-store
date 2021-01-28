package com.setge.sandwichstore.data;

import com.setge.sandwichstore.domain.Sandwich;
import org.springframework.data.repository.CrudRepository;

public interface SandwichRepository extends CrudRepository<Sandwich, Long> {

}
