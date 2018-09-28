package com.wada.balanco.login.repository;

import com.wada.balanco.login.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserCustom, Long> {

    /**
     *  Encontra {@link UserCustom} por email.
     * @param email
     * @return
     */
    UserCustom findByEmail(String email);

}