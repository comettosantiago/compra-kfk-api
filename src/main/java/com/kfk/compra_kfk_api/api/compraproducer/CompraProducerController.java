package com.kfk.compra_kfk_api.api.compraproducer;

import com.kfk.compra_kfk_api.api.compraproducer.entities.Checkout;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraProducerController {

    private final CompraProducerService compraProducerService;

    public CompraProducerController(CompraProducerService compraProducerService) {
        this.compraProducerService = compraProducerService;
    }

    @PostMapping("/compras")
    public ResponseEntity<String> publicarCompra(@RequestBody Checkout checkout) {
        compraProducerService.publicarCompra(checkout);
        return new ResponseEntity<>("Compra realizada", HttpStatus.CREATED);
    }

}
