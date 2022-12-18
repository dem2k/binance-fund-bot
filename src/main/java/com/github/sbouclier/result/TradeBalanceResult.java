package com.github.sbouclier.result;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Result from getTradeBalance
 *
 * @author Stéphane Bouclier
 */
public class TradeBalanceResult extends Result<TradeBalanceResult.TradeBalance> {

    public static class TradeBalance {

        @JsonProperty("eb")
        public BigDecimal equivalentBalance;

        @JsonProperty("tb")
        public BigDecimal tradeBalance;

        @JsonProperty("m")
        public BigDecimal marginAmount;

        @JsonProperty("n")
        public BigDecimal unrealizedNetProfitLoss;

        @JsonProperty("c")
        public BigDecimal costBasis;

        @JsonProperty("v")
        public BigDecimal floatingValuation;

        @JsonProperty("e")
        public BigDecimal equity;

        @JsonProperty("mf")
        public BigDecimal freeMargin;

        @JsonProperty("ml")
        public BigDecimal marginLevel;

        @Override
        public String toString() {
            return "TradeBalanceResult{" +
                    "equivalentBalance=" + equivalentBalance +
                    ", tradeBalance=" + tradeBalance +
                    ", marginAmount=" + marginAmount +
                    ", unrealizedNetProfitLoss=" + unrealizedNetProfitLoss +
                    ", costBasis=" + costBasis +
                    ", floatingValuation=" + floatingValuation +
                    ", equity=" + equity +
                    ", freeMargin=" + freeMargin +
                    ", marginLevel=" + marginLevel +
                    "}";
        }
    }
}
