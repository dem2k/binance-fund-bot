package com.github.sbouclier.common;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Ledger information
 *
 * @author Stéphane Bouclier
 */
public class LedgerInformation {

    @JsonProperty("refid")
    public String referenceId;

    @JsonProperty("time")
    public Long timestamp;

    public String type;

    @JsonProperty("aclass")
    public String assetClass;

    public String asset;

    public BigDecimal amount;

    public BigDecimal fee;

    public BigDecimal balance;

    @Override
    public String toString() {
        return "LedgerInformation{" +
                "referenceId='" + referenceId + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", assetClass='" + assetClass + '\'' +
                ", asset='" + asset + '\'' +
                ", amount=" + amount +
                ", fee=" + fee +
                ", balance=" + balance +
                "}";
    }
}
