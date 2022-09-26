package com.wmpv.project.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente", schema = "public", catalog = "configuration")
public class ClienteEntity extends PersonaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "contrasena", nullable = false, length = 30)
    private String contrasena;

    @Basic
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
    
    
}
