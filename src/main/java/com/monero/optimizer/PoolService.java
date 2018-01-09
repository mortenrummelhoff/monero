package com.monero.optimizer;

import com.monero.optimizer.clients.HashVaultProClient;
import com.monero.optimizer.clients.MineXmrClient;
import com.monero.optimizer.clients.data.HashVaultProStatsData;
import com.monero.optimizer.clients.executer.MinerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.TimeZone;

@Configuration
@EnableScheduling
public class PoolService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HashVaultProClient hashVaultProClient;

    @Autowired
    private MineXmrClient mineXmrClient;

    @Autowired
    private MinerTool minerTool;

    @Value("${monero.block.hours.max}")
    private Integer hoursMax;

    @Scheduled(fixedDelay = 60000, initialDelay = 0)
    public void checkPoolStats() {

        boolean minerStarted = minerTool.isMinerStarted();
        if (StringUtils.isEmpty(minerTool.getMinerName())) {
            logger.info("No Miner is not started...");
        } else {
            logger.info("Miner: " + minerTool.getMinerName() + " Is alive: " + minerStarted);
        }

        logger.info("Checking pool stats");
        HashVaultProStatsData poolStats = hashVaultProClient.getPoolStats();
        logger.info("HashvaultPro stats: " + poolStats);

        String minePoolStats = mineXmrClient.getPoolStats();
        logger.info("MineXmr Stats: " + minePoolStats);

        Calendar lastBLockFoundTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        lastBLockFoundTime.setTimeInMillis(poolStats.pool_statistics.lastBlockFoundTime * 1000);

        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        long timeSinceInMillis = currentTime.getTimeInMillis() - lastBLockFoundTime.getTimeInMillis();

        int hours = (int) timeSinceInMillis / (1000*60*60);

        logger.info("Monero Last Block Found: " + lastBLockFoundTime.getTime() + ", Hours: " + hours);

        if (minerStarted) {
            if (hours > hoursMax) {
                //check if Aeon is running
                if ("Monero".equals(minerTool.getMinerName())) {
                    minerTool.startMiner("Aeon");
                } else {
                    logger.info("Everything fine...Mining: " + minerTool.getMinerName());
                }
            } else {
                if ("Aeon".equals(minerTool.getMinerName())) {
                    minerTool.startMiner("Monero");
                } else {
                    logger.info("Everything fine...Mining: " + minerTool.getMinerName());
                }
            }
        } else {
             minerTool.startMiner((hours > hoursMax) ? "Aeon" : "Monero");
        }

    }


}
