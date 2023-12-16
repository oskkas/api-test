package org.oskkar.crudapirest.repository;

import org.oskkar.crudapirest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
