package com.wmpv.project.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class PersonaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "identificacion", nullable = true, length = 10)
    private String identificacion;

    @Basic
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Basic
    @Column(name = "genero", nullable = true, length = 1)
    private String genero;

    @Basic
    @Column(name = "direccion", nullable = true, length = 80)
    private String direccion;

    @Basic
    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;

    @Basic
    @Column(name = "edad", nullable = true)
    private Integer edad;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonaEntity that = (PersonaEntity) o;

        if (idCliente != that.idCliente) return false;
        if (!Objects.equals(identificacion, that.identificacion))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (identificacion != null ? identificacion.hashCode() : 0);
        return result;
    }
}
