package com.kfk.compra_kfk_api.api.compraproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.kfk.compra_kfk_api.api.compraproducer.entities.Checkout;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.Compra;
import com.kfk.compra_kfk_api.api.compraproducer.entities.compra.CompraRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompraProducerServiceImpl implements CompraProducerService {

    private final CompraRepository compraRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${kafka.topic.name:compra.realizada}")
    private String topicName;

    public CompraProducerServiceImpl(CompraRepository compraRepository, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.compraRepository = compraRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publicarCompra(Checkout checkout) {
        Compra compra = compraRepository.save(new Compra(checkout.getDescription(), checkout.getPrice()));
        try {
            String compraJson = objectMapper.writeValueAsString(compra);
            kafkaTemplate.send(topicName, compraJson);
            System.out.println("Compra publicada en el topic: " + topicName);
        } catch (JsonProcessingException e) {
            System.err.println("Error serializando la compra: " + e.getMessage());
        }
    }

}


























