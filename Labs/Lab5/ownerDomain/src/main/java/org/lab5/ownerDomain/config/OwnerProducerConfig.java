package org.lab5.ownerDomain.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.lab5.dataAccess.dto.CatList;
import org.lab5.dataAccess.dto.OwnerDto;
import org.lab5.dataAccess.dto.OwnerListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class OwnerProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, Object> producerFactoryJson()
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, OwnerDto> getOwner(ProducerFactory<String, OwnerDto> producerFactoryJson)
    {
        return new KafkaTemplate<>(producerFactoryJson);
    }

    @Bean
    public KafkaTemplate<String, OwnerListDto> getOwners(ProducerFactory<String, OwnerListDto> producerFactoryJson)
    {
        return new KafkaTemplate<>(producerFactoryJson);
    }

    @Bean
    public KafkaTemplate<String, CatList> getCats(ProducerFactory<String, CatList> producerFactoryJson)
    {
        return new KafkaTemplate<>(producerFactoryJson);
    }
}