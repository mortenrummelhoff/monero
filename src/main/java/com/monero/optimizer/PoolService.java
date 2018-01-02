package com.monero.optimizer;

import com.monero.optimizer.clients.HashVaultProClient;
import com.monero.optimizer.clients.MineXmrClient;
import com.monero.optimizer.clients.data.HashVaultProStatsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class PoolService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HashVaultProClient hashVaultProClient;

    @Autowired
    private MineXmrClient mineXmrClient;

    @Scheduled(fixedDelay = 5000)
    public void checkPoolStats() {

        logger.info("Checking pool stats");

        HashVaultProStatsData poolStats = hashVaultProClient.getPoolStats();
        logger.info("HashvaultPro stats: " + poolStats);

        String minePoolStats = mineXmrClient.getPoolStats();
        logger.info("MineXmr Stats: " + minePoolStats);
    }


}
