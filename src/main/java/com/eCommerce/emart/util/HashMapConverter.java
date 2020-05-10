package com.eCommerce.emart.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Mayur on 09/05/20.
 */
@Slf4j
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Map<String, Object> productQuantityInfo) {
    String productQuantityInfoJson = null;
    try {
      productQuantityInfoJson = objectMapper.writeValueAsString(productQuantityInfo);
    } catch (final JsonProcessingException e) {
      log.error("JSON writing error", e);
    }
    return productQuantityInfoJson;
  }

  @Override
  public Map<String, Object> convertToEntityAttribute(String productQuantityInfoJson) {
    Map<String, Object> productQuantityInfo = null;
    try {
      productQuantityInfo = objectMapper.readValue(productQuantityInfoJson, Map.class);
    } catch (final IOException e) {
      log.error("JSON reading error", e);
    }
    return productQuantityInfo;
  }

}
