package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args)
    {
        try {
            // URL da API
            String url = "https://jsonplaceholder.typicode.com/posts";

            // Criando um objeto User
            User user = new User(1, 1, "delectus aut autem", false);

            // Convertendo o objeto User para JSON usando ObjectMapper
            String jsonInputString = convertObjectToJson(user);

            // Fazendo a requisição POST
            fazerRequisicao(url, jsonInputString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fazerRequisicao(String url, String jsonInputString) throws Exception
    {
        // Criando a URL
        URL obj = new URL(url);

        // Abrindo a conexão HTTP
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Configurando o método da requisição
        con.setRequestMethod("POST");

        // Configurando o cabeçalho
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Enviando o JSON no corpo da requisição
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes();
            os.write(input, 0, input.length);
        }

        // Obtendo a resposta
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED) { // Código 201 indica que o recurso foi criado
            System.out.println("Requisição POST enviada com sucesso. Código de resposta: " + responseCode);
        } else {
            System.out.println("Falha na requisição POST. Código de resposta: " + responseCode);
        }
    }

    private static String convertObjectToJson(User user) throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }
}

/*--------------------------------------------------------------------------------------------*/
/**package org.example;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            String url = "https://jsonplaceholder.typicode.com/posts";

            //GERANDO O DOCUMENTO JSON COM OS VALORES
            String jsonEntradaString = "{ \"userId\": 1, \"id\": 1, \"title\": \"delectus aut autem\", \"completed\": false }";

            gerarRequisicao(url, jsonEntradaString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void gerarRequisicao(String url, String jsonEntradaString) throws Exception
    {
        URL urlCriada = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlCriada.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream())
        {
            byte[] entrada = jsonEntradaString.getBytes(StandardCharsets.UTF_8);
            outputStream.write(entrada, 0, entrada.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED)
        {
            System.out.println("Requisição POST enviada com sucesso. Código de resposta: " + responseCode);
        }
        else
        {
            System.out.println("Falha na requisição POST. Código de resposta: " + responseCode);
        }
    }
}
/*--------------------------------------------------------------------------------------------*/

/**package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try
        {
            String url = "http://universities.hipolabs.com/search?country=Brazil";
            List<UniversidadeObjectMapper> universidadeObjectMapperList = gerarRequisicao(url);

            for (UniversidadeObjectMapper universidadeObjectMapper : universidadeObjectMapperList)
            {
                System.out.println(universidadeObjectMapper);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static List<UniversidadeObjectMapper> gerarRequisicao(String url) throws Exception {
        URL urlCriada = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlCriada.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            ObjectMapper objectMapper = new ObjectMapper();

            List<UniversidadeObjectMapper> universidadeObjectMapperList = objectMapper.readValue(connection.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, UniversidadeObjectMapper.class));

            return universidadeObjectMapperList;
        }
        else
        {
            throw new RuntimeException("Falha na requisição, Código de resposta: " + responseCode);
        }
    }
}
/*--------------------------------------------------------------------------------------------*/

/**package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try
        {
            String url = "http://universities.hipolabs.com/search?country=Brazil";

            List<Universidade> universidades = gerarRequisicao(url);

            for(Universidade universidade : universidades)
            {
                System.out.println(universidade);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static List<Universidade> gerarRequisicao(String url) throws Exception
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

            JSONArray jsonArray = new JSONArray(conteudo.toString());

            List<Universidade> universidades = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nome = jsonObject.getString("name");
                String urlUniversidade = "";
                if (jsonObject.has("web_pages") && jsonObject.get("web_pages") instanceof JSONArray)
                {
                    JSONArray webPagesArray = jsonObject.getJSONArray("web_pages");
                    if (webPagesArray.length() > 0)
                    {
                        urlUniversidade = webPagesArray.getString(0);
                    }
                }
                Universidade universidade = new Universidade(nome, urlUniversidade);
                universidades.add(universidade);
            }

            return universidades;
        }
        else
        {
            throw new RuntimeException("Falha na requisição, Código de resposta: " + responseCode);
        }
    }
}*/

/*--------------------------------------------------------------------------------------------*/
/**package org.example;

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
}*/

/*--------------------------------------------------------------------------------------------*/
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

/*--------------------------------------------------------------------------------------------*/
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