package com.tr.auth.repository;

import com.tr.auth.entity.UserRole;
import com.tr.auth.entity.pk.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK>, JpaSpecificationExecutor<UserRole> {
}
