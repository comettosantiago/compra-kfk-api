package com.kfk.compra_kfk_api.api.compra;

import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.Compra;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.CompraRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

    private final CompraRepository compraRepository;

    public CompraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @GetMapping("/compras/{id}")
    public Compra getCompras(@PathVariable Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }


}
