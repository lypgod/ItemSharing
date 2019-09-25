package com.lypgod.itemsharing.itemservice.model.repository;

import com.lypgod.itemsharing.itemservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lypgod
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
