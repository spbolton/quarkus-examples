package org.acme.crud.hibernate.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
// If autoApply is not set then need to annotate the JsonNode entity fields with the following
// @Convert(converter = JsonAttributeConverter.class)
public class JsonAttributeConverter implements AttributeConverter<JsonNode, String> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode node) {
        return node.toString();
    }

    @Override
    public JsonNode convertToEntityAttribute(String node) {
        try {
            return mapper.readTree(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

