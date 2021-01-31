package com.setge.sandwichstore.data;

import com.setge.sandwichstore.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username); // id로 User를 찾는다.
}
