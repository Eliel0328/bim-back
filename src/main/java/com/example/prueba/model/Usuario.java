package com.example.prueba.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotNull(message = "Not Null")
    @NotBlank(message = "Correo Obligatorio")
    @Size(min = 8, max = 200, message = "Longitud valida (8-200)")
    @Email(message = "Correo invalido")
    @Column(name = "correo", nullable = false, length = 200)
    private String correo;

    @NotNull(message = "Not Null")
    @NotBlank(message = "Usuario Obligatorio")
    @Size(min = 8, max = 20, message = "Longitud valida (8-20)")
    @Column(name = "usuario", nullable = false, length = 20)
    private String usuario;

    @NotNull(message = "Not Null")
    @NotBlank(message = "Contraseña Obligatorio")
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    public Usuario() {
    }

    public Usuario(Integer idUsuario,
            @NotNull(message = "Not Null") @NotBlank(message = "Correo Obligatorio") @Size(min = 8, max = 200, message = "Longitud valida (8-200)") @Email(message = "Correo invalido") String correo,
            @NotNull(message = "Not Null") @NotBlank(message = "Usuario Obligatorio") @Size(min = 8, max = 20, message = "Longitud valida (8-20)") String usuario,
            @NotNull(message = "Not Null") @NotBlank(message = "Contraseña Obligatorio") String password) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        result = prime * result + ((correo == null) ? 0 : correo.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (idUsuario == null) {
            if (other.idUsuario != null)
                return false;
        } else if (!idUsuario.equals(other.idUsuario))
            return false;
        if (correo == null) {
            if (other.correo != null)
                return false;
        } else if (!correo.equals(other.correo))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", correo=" + correo + ", usuario=" + usuario + ", password="
                + password + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new Authority("ROLE_USER"));
        return roles;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}