package com.kfk.compra_kfk_api.api.envioconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfk.compra_kfk_api.api.compraproducer.entities.Envio;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.Compra;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.CompraRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EnvioConsumerServiceImpl implements EnvioConsumerService {

    private final CompraRepository compraRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EnvioConsumerServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @KafkaListener(topics = "envio.generado", groupId = "group-envios")
    public void consume(String json) throws JsonProcessingException {
        Envio envio =  objectMapper.readValue(json, Envio.class);
        Compra compra = compraRepository.findById(envio.getCompraId())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + envio.getCompraId()));
        compra.setEnvio(envio);
        compraRepository.save(compra);
        System.out.println("Compra actualizada con el envío: " + compra);
    }

}
