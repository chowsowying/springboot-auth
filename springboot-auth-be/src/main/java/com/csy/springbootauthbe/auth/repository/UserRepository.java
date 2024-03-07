package com.csy.springbootauthbe.auth.repository;

//Imports
import com.csy.springbootauthbe.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Desc:
 * This interface provides access to user data stored in a database
 * It extends JpaRepository interface, which provides basic CRUD operations
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
