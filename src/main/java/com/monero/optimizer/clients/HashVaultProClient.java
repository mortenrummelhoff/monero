package com.monero.optimizer.clients;

import com.monero.optimizer.clients.data.HashVaultProStatsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HashVaultProClient {

    @Autowired
    RestTemplate restTemplate;

    private String host = "https://monero.hashvault.pro";
    private String poolStatMethod = "/api/pool/stats";

    public HashVaultProStatsData getPoolStats() {


        return restTemplate.exchange(host + poolStatMethod, HttpMethod.GET, null, HashVaultProStatsData.class).getBody();
    }

}
