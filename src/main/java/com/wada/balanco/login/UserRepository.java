package com.wada.balanco.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *  Encontra {@link User} por email.
     * @param email
     * @return
     */
    User findByEmail(String email);

}