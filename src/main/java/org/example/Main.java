package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            String url = "https://api.publicapis.org/entries";
            RespostaJSON respostaJSON = gerarRequisicao(url);
            System.out.println(respostaJSON.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static RespostaJSON gerarRequisicao(String url) throws Exception
    {
        URL urlCriada = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlCriada.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String entradaLine;
            StringBuilder conteudo = new StringBuilder();

            while ((entradaLine = entrada.readLine()) != null)
            {
                conteudo.append(entradaLine);
            }
            entrada.close();

            RespostaJSON respostaJSON = new RespostaJSON();
            respostaJSON.setRespostaJson(conteudo.toString());

            return respostaJSON;
        }
        else
        {
            throw new RuntimeException("Falha na solicitação de requisição - CÓDIGO : " + responseCode);
        }
    }
}