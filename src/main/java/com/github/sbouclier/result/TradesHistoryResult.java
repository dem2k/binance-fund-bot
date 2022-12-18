package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sbouclier.common.OrderDirection;
import com.github.sbouclier.common.OrderType;

/**
 * Result from getTradesHistory
 *
 * @author Stéphane Bouclier
 */
public class TradesHistoryResult extends Result<TradesHistoryResult.TradesHistory> {

    public static class TradesHistory {

        @JsonProperty("trades")
        public Map<String, TradeHistory> trades;

        public Long count;

        @Override
        public String toString() {
            return "TradesHistory{" +
                    "trades=" + trades +
                    ", count=" + count +
                    "}";
        }
    }

    // TODO extract to TradeInformation
    public static class TradeHistory {

        @JsonProperty("ordertxid")
        public String orderTransactionId;

        @JsonProperty("pair")
        public String assetPair;

        @JsonProperty("time")
        public Long tradeTimestamp;

        @JsonProperty("type")
        public OrderDirection orderDirection;

        @JsonProperty("ordertype")
        public OrderType orderType;

        public BigDecimal price;

        public BigDecimal cost;

        public BigDecimal fee;

        @JsonProperty("vol")
        public BigDecimal volume;

        public BigDecimal margin;

        @JsonProperty("misc")
        public String miscellaneous;

        @Override
        public String toString() {
            return "TradeHistory{" +
                    "orderTransactionId='" + orderTransactionId + '\'' +
                    ", assetPair='" + assetPair + '\'' +
                    ", tradeTimestamp=" + tradeTimestamp +
                    ", orderDirection=" + orderDirection +
                    ", orderType=" + orderType +
                    ", price=" + price +
                    ", cost=" + cost +
                    ", fee=" + fee +
                    ", volume=" + volume +
                    ", margin=" + margin +
                    ", miscellaneous='" + miscellaneous + '\'' +
                    "}";
        }
    }
}
