package com.tr.auth.repository;

import com.tr.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value = "select id from user where username = :username")
    Integer findIdByUsername(String username);

}
