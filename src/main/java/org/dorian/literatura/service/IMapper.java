package org.dorian.literatura.service;

public interface IMapper {
    <T> T obtenerDatos(String json, Class<T> clase);
}
