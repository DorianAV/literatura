package org.dorian.literatura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dorian.literatura.model.DatosLibro;
import org.dorian.literatura.model.RespuestaGutendex;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private Mapper mapper= new Mapper();
    public DatosLibro obtenerDatos(String nombreLibro) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=" + nombreLibro))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            RespuestaGutendex respuesta = mapper.obtenerDatos(json, RespuestaGutendex.class);

            if (respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
                System.out.println("No se encontraron libros con ese nombre.");
                return null;
            }

            // Devolver el primer libro
            return respuesta.getResults().get(0);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al hacer la solicitud a Gutendex", e);
        }
    }


}
