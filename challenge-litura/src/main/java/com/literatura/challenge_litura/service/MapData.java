package com.literatura.challenge_litura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MapData implements IMapData{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T mapData(String json, Class<T> clase) throws JsonProcessingException {
        return objectMapper.readValue(json, clase);
    }
}
