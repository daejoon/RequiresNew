package com.ddoong2.requiresnew.repository;

import com.ddoong2.requiresnew.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
