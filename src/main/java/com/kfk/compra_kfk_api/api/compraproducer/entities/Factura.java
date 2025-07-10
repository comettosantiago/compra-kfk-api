package com.kfk.compra_kfk_api.api.compraproducer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura", schema = "kfk")
public class Factura {
    @Id
    private Long id;
    private Long compraId;
    private String tipoFactura;
    private String numeroCAE;

    public Factura() {
    }

    public Factura(Long compraId, String tipoFactura, String numeroCAE) {
        this.compraId = compraId;
        this.tipoFactura = tipoFactura;
        this.numeroCAE = numeroCAE;
    }
}
