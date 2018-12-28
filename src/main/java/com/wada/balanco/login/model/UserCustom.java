package com.wada.balanco.login.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "usercustom")
public class UserCustom {

    private Long id;
    private String email;
    private String password;
    private String nome;
    private String sobrenome;
    private Boolean ativo;
    private Collection<Role> roles;

    public UserCustom() {
    }

    public UserCustom(String email, String password, String nome, String sobrenome, Boolean ativo, Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.ativo = ativo;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Email(message = "*Por favor, forneça um e-mail válido")
    @NotEmpty(message = "*Por favor, forneça um e-mail")
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    @Length(min = 5, message = "*A senha deve possuir, no mínimo, 5 caracteres")
    @NotEmpty(message = "*Por favor, cadastre uma senha")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "nome")
    @NotEmpty(message = "*Por favor, forneça seu nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "sobrenome")
    @NotEmpty(message = "*Por favor, forneça seu sobrenome")
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Column(name = "ativo")
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
