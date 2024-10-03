package com.alura.cursos.modelos;


import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    private String claveApi = "63b8da58e2a753b83c82188f";

        public Moneda buscaMoneda(String base,String target,double cantidad){

         String direccion = String.format("https://v6.exchangerate-api.com/v6/"+claveApi+"/pair/"+base+"/"+target+"/"+cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response;
        {
            try {
                response = client.
                        send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), Moneda.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
