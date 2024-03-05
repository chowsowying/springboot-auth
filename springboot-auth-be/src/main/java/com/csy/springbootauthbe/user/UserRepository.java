package com.csy.springbootauthbe.user;

//Imports
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Desc:
 * This interface provides access to user data stored in a database
 * It extends JpaRepository interface, which provides basic CRUD operations
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}