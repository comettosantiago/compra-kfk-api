package com.kfk.compra_kfk_api.api.facturaconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfk.compra_kfk_api.api.compraproducer.entities.Envio;
import com.kfk.compra_kfk_api.api.compraproducer.entities.Factura;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.Compra;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.CompraRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FacturaConsumerServiceImpl implements FacturaConsumerService {

    private final CompraRepository compraRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FacturaConsumerServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @KafkaListener(topics = "factura.generada", groupId = "group-facturas")
    public void consume(String json) throws JsonProcessingException {
        Factura factura =  objectMapper.readValue(json, Factura.class);
        Compra compra = compraRepository.findById(factura.getCompraId())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + factura.getCompraId()));
        compra.setFactura(factura);
        compraRepository.save(compra);
        System.out.println("Compra actualizada con factura: " + compra);
    }

}
