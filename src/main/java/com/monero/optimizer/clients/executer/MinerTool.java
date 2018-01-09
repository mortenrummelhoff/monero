package com.monero.optimizer.clients.executer;

import com.monero.optimizer.clients.executer.miners.AeonMiner;
import com.monero.optimizer.clients.executer.miners.Miner;
import com.monero.optimizer.clients.executer.miners.MoneroMiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;

@Component
public class MinerTool {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Process minerProcess;
    private String name;

    @Autowired
    private MoneroMiner moneroMiner;

    @Autowired
    private AeonMiner aeonMiner;

    public BufferedReader startMiner(String name) {
        this.name = name;

        logger.info("Starting miner: " + name);

        closeMinerProcess();
        ProcessBuilder builder = new ProcessBuilder();
        Miner miner = getMiner(name);

        logger.info("Using Command: " + miner.getCommand());

        String[] commands = miner.getCommand().split(" ");

        builder.command(commands);
        //Arrays.stream(commands).forEach();
        //builder.command(miner.getCommand());

        logger.info("In Directory: " + miner.getDirectory());
        builder.directory(new File(miner.getDirectory()));
        try {
            minerProcess = builder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(minerProcess.getInputStream(), logger::info);
            Executors.newSingleThreadExecutor().submit(streamGobbler);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Started Miner...." + minerProcess.isAlive());

        return null;

    }

    private void closeMinerProcess() {
        if (null != minerProcess) {
            logger.info("MinerProcess isAlive: " + minerProcess.isAlive());
            minerProcess.isAlive();
            minerProcess.destroyForcibly();
        }
    }

    public boolean isMinerStarted() {
        return minerProcess != null && minerProcess.isAlive();
    }

    public String getMinerName() {
        return name;
    }

    private Miner getMiner(String name) {
        if ("Monero".equals(name)) {
            return moneroMiner;
        } else {
            return aeonMiner;
        }
    }


}
