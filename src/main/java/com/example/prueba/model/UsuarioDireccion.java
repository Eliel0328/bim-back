package com.example.prueba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario_direccion")
public class UsuarioDireccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioDireccion;

    @Size(max = 150, message = "Longitud valida (0-150)")
    @Column(name = "estado", length = 150)
    private String estado;

    @Size(max = 150, message = "Longitud valida (0-150)")
    @Column(name = "municipio", length = 150)
    private String municipio;

    @Column(name = "cp")
    private Integer cp;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public UsuarioDireccion() {
    }

    public UsuarioDireccion(
            @Size(max = 150, message = "Longitud valida (0-150)") String estado,
            @Size(max = 150, message = "Longitud valida (0-150)") String municipio,
            Integer cp, Usuario usuario) {
        this.estado = estado;
        this.municipio = municipio;
        this.cp = cp;
        this.usuario = usuario;
    }

    public Integer getIdUsuarioDireccion() {
        return idUsuarioDireccion;
    }

    public void setIdUsuarioDireccion(Integer idUsuarioDireccion) {
        this.idUsuarioDireccion = idUsuarioDireccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idUsuarioDireccion == null) ? 0 : idUsuarioDireccion.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
        result = prime * result + ((cp == null) ? 0 : cp.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
        UsuarioDireccion other = (UsuarioDireccion) obj;
        if (idUsuarioDireccion == null) {
            if (other.idUsuarioDireccion != null)
                return false;
        } else if (!idUsuarioDireccion.equals(other.idUsuarioDireccion))
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (municipio == null) {
            if (other.municipio != null)
                return false;
        } else if (!municipio.equals(other.municipio))
            return false;
        if (cp == null) {
            if (other.cp != null)
                return false;
        } else if (!cp.equals(other.cp))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioDireccion [idUsuarioDireccion=" + idUsuarioDireccion + ", estado=" + estado + ", municipio="
                + municipio + ", cp=" + cp + ", usuario=" + usuario + "]";
    }

}