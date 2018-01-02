package com.monero.optimizer.clients.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public final class HashVaultProStatsData {
    public final String[] pool_list;
    public final Pool_statistics pool_statistics;
    public final long last_payment;

    @JsonCreator
    public HashVaultProStatsData(@JsonProperty("pool_list") String[] pool_list, @JsonProperty("pool_statistics") Pool_statistics pool_statistics, @JsonProperty("last_payment") long last_payment){
        this.pool_list = pool_list;
        this.pool_statistics = pool_statistics;
        this.last_payment = last_payment;
    }

    public static final class Pool_statistics {
        public final long hashRate;
        public final long miners;
        public final long totalHashes;
        public final long lastBlockFoundTime;
        public final long lastBlockFound;
        public final long totalBlocksFound;
        public final long totalMinersPaid;
        public final long totalPayments;
        public final long roundHashes;

        @JsonCreator
        public Pool_statistics(@JsonProperty("hashRate") long hashRate, @JsonProperty("miners") long miners, @JsonProperty("totalHashes") long totalHashes, @JsonProperty("lastBlockFoundTime") long lastBlockFoundTime, @JsonProperty("lastBlockFound") long lastBlockFound, @JsonProperty("totalBlocksFound") long totalBlocksFound, @JsonProperty("totalMinersPaid") long totalMinersPaid, @JsonProperty("totalPayments") long totalPayments, @JsonProperty("roundHashes") long roundHashes){
            this.hashRate = hashRate;
            this.miners = miners;
            this.totalHashes = totalHashes;
            this.lastBlockFoundTime = lastBlockFoundTime;
            this.lastBlockFound = lastBlockFound;
            this.totalBlocksFound = totalBlocksFound;
            this.totalMinersPaid = totalMinersPaid;
            this.totalPayments = totalPayments;
            this.roundHashes = roundHashes;
        }

        @Override
        public String toString() {
            return "Pool_statistics{" +
                    "hashRate=" + hashRate +
                    ", miners=" + miners +
                    ", totalHashes=" + totalHashes +
                    ", lastBlockFoundTime=" + lastBlockFoundTime +
                    ", lastBlockFound=" + lastBlockFound +
                    ", totalBlocksFound=" + totalBlocksFound +
                    ", totalMinersPaid=" + totalMinersPaid +
                    ", totalPayments=" + totalPayments +
                    ", roundHashes=" + roundHashes +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HashVaultProStatsData{" +
                "pool_list=" + Arrays.toString(pool_list) +
                ", pool_statistics=" + pool_statistics +
                ", last_payment=" + last_payment +
                '}';
    }
}