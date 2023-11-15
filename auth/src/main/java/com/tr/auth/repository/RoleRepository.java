package com.tr.auth.repository;

import com.tr.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    Role findByRolename(String rolename);

    @Query(value = "select * from role where id in (select role_id from user_role where user_id = :userId)", nativeQuery = true)
    List<Role> findRolesByUserId(Integer userId);

}
