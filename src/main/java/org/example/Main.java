package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet getRequest = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
            try(CloseableHttpResponse response = client.execute(getRequest)){
                var responseBody = response.getEntity().getContent();
                ObjectMapper mapper = new ObjectMapper();
                List.of(mapper.readValue(responseBody, Cat[].class)).stream()
                        .filter(cat -> cat.getUpvotes() != null && cat.getUpvotes() != 0)
                        .forEach(System.out::println);
            }
        }
    }
}