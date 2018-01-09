package com.monero.optimizer.clients.executer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

public class MinerTool {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Process minerProcess;
    private String name;

    public BufferedReader startMiner(String name) {
        this.name = name;

        logger.info("Starting miner: " + name);

        closeMinerProcess();
        ProcessBuilder builder = new ProcessBuilder();

        builder.command("./xmr-stak-cpu");
        builder.directory(new File("/Users/mortenrummelhoff/Downloads/v1/xmr-stak-full"));
        try {
            minerProcess = builder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(minerProcess.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Started Miner...." + minerProcess.isAlive());

        return null;

    }

    private void closeMinerProcess() {
        if (null != minerProcess) {
            System.out.println("MinerProcess isAlive: " + minerProcess.isAlive());
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

}
