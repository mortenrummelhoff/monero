package com.monero.optimizer.clients.executer.miners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AeonMiner implements Miner {

    @Value("${aeon.miner.command}")
    private String command;

    @Value("${aeon.miner.directory}")
    private String directory;

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getDirectory() {
        return directory;
    }
}
