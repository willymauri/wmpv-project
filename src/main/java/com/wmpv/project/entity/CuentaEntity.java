package com.wmpv.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cuenta", schema = "public", catalog = "configuration")
public class CuentaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta", nullable = false)
    private Integer idCuenta;

    @Column(name = "numero", nullable = true, length = 30)
    private String numero;

    @Basic
    @Column(name = "tipo_cuenta", nullable = true, length = 1)
    private String tipoCuenta;
    
    @Basic
    @Column(name = "estado", nullable = true, length = 1)
    private String estado;
    
    @Basic
    @Column(name = "saldo_inicial", nullable = true, length = 1)
    private BigDecimal saldoInicial;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private ClienteEntity cliente;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity that = (CuentaEntity) o;

        if (idCuenta != that.idCuenta) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idCuenta != null ? idCuenta.hashCode() : 0;
        return result;
    }
}
