package com.monero.optimizer.clients;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MineXmrClient {


    private String host = "http://stats.minexmr.com";
    private String poolStatMethod = "/stats";

    public String getPoolStats() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "GZIP");


        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        HttpEntity<?> entity = new HttpEntity(headers);

        return restTemplate.exchange(host + poolStatMethod, HttpMethod.GET, entity, String.class).getBody();
    }


}
