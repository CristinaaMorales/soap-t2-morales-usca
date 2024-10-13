package com.cibertec.edu.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pagos")

public class PagoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idpago;

    @Column(name = "p_fecha_pago", nullable = false)
    private String fechapago;

    @Column(name = "p_monto_total", nullable = false)
    private double montototal;

    @Column(name = "p_moneda", length=10, nullable = false)
    private String moneda;

}
