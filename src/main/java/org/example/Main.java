package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {
    public static void main(String[] args)
    {
        try
        {
            String nome = "Talita";
            String url = "https://api.agify.io/?name=" + nome;
            PessoaObjectMapper pessoaObjectMapper = gerarRequisicao(url);

            System.out.println(pessoaObjectMapper);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static PessoaObjectMapper gerarRequisicao(String url) throws Exception
    {
        URL urlCriada = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlCriada.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            ObjectMapper objectMapper = new ObjectMapper();

            PessoaObjectMapper pessoaObjectMapper = objectMapper.readValue(connection.getInputStream(), PessoaObjectMapper.class);

            return pessoaObjectMapper;
        }
        else
        {
            throw new RuntimeException("Falha na solicitação da requisição! Código: " + responseCode);
        }
    }
}

/*
    REQUEST COM HttpURLConnection;
**/

/**package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            //Nome para pesquisa
            String nome = "Uendel";

            //URL da API
            String url = "https://api.agify.io/?name=" + nome;

            //Solicitando a requisição
            Pessoa pessoa = solicitarRequisicao(url);

            //Imprime o objeto PESSOA
            System.out.println(pessoa);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static Pessoa solicitarRequisicao(String url) throws Exception
    {
        // Gerando a URL
        URL urlCriada = new URL(url);

        //Abertura da conexão HTTP
        HttpURLConnection connection = (HttpURLConnection) urlCriada.openConnection();

        // Configuração do método de requisição
        connection.setRequestMethod("GET");

        // Obtenção da resposta
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

            //Criação do objeto JSONObject a partir da resposta JSON
            JSONObject jsonObject = new JSONObject(conteudo.toString());

            // Leitura dos valores JSON
            String nome = jsonObject.getString("name");
            int idade = jsonObject.getInt("age");

            // Gerando uma instancia de Pessoa
            return new Pessoa(nome, idade);
        }
        else
        {
            throw new RuntimeException("Falha na requisição, Código de resposta: " + responseCode);
        }
    }
}*/

/**

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
}*/