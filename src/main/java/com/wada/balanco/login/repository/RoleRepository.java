package com.wada.balanco.login.repository;

import com.wada.balanco.login.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Encontra {@link Role} por descrição
     * @param descricao
     * @return
     */
    Role findByDescricao(String descricao);


}
