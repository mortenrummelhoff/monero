package com.monero.optimizer.clients.executer.miners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MoneroMiner implements Miner {

    @Value("${monero.miner.command}")
    private String command;

    @Value("${monero.miner.directory}")
    private String directory;


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
