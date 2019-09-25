package com.lypgod.itemsharing.zipkinserver.model.repository;

import com.lypgod.itemsharing.zipkinserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lypgod
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find user By Username
     *
     * @param username String
     * @return User
     */
    User findByUsername(String username);

    /**
     * exists By Username
     *
     * @param username String
     * @return boolean
     */
    boolean existsByUsername(String username);
}
