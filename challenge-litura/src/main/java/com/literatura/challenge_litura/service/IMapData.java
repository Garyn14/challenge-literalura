package com.literatura.challenge_litura.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IMapData {
    <T> T mapData(String json, Class<T> clase) throws JsonProcessingException;
}
