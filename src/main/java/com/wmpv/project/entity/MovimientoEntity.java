package com.wmpv.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "movimiento", schema = "public", catalog = "configuration")
public class MovimientoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento", nullable = false)
    private Integer idMovimiento;

    @Column(name = "tipo", nullable = true, length = 1)
    private String tipo;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = true)
    private Date fecha;
    
    @Basic
    @Column(name = "saldo", nullable = true)
    private BigDecimal saldo;
    
    @Basic
    @Column(name = "valor", nullable = true)
    private BigDecimal valor;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    private CuentaEntity cuenta;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovimientoEntity that = (MovimientoEntity) o;

        if (idMovimiento != that.idMovimiento) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idMovimiento != null ? idMovimiento.hashCode() : 0;
        return result;
    }
}
