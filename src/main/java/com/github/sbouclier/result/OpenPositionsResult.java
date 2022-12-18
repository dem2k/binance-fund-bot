package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sbouclier.common.OrderDirection;
import com.github.sbouclier.common.OrderType;

/**
 * Result from getOpenPositions
 *
 * @author Stéphane Bouclier
 */
public class OpenPositionsResult extends Result<Map<String, OpenPositionsResult.OpenPosition>> {

    public static class OpenPosition {

        @JsonProperty("ordertxid")
        public String orderTransactionId;

        @JsonProperty("posstatus")
        public String positionStatus;

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

        @JsonProperty("vol_closed")
        public BigDecimal volumeCosed;

        public BigDecimal margin;

        public String terms;

        @JsonProperty("rollovertm")
        public Long rolloverTimestamp;

        @JsonProperty("misc")
        public String miscellaneous;

        @JsonProperty("oflags")
        public String orderFlags;

        @Override
        public String toString() {
            return "OpenPosition{" +
                    "orderTransactionId='" + orderTransactionId + '\'' +
                    ", positionStatus='" + positionStatus + '\'' +
                    ", assetPair='" + assetPair + '\'' +
                    ", tradeTimestamp=" + tradeTimestamp +
                    ", orderDirection=" + orderDirection +
                    ", orderType=" + orderType +
                    ", price=" + price +
                    ", cost=" + cost +
                    ", fee=" + fee +
                    ", volume=" + volume +
                    ", volumeCosed=" + volumeCosed +
                    ", margin=" + margin +
                    ", terms='" + terms + '\'' +
                    ", rolloverTimestamp=" + rolloverTimestamp +
                    ", miscellaneous='" + miscellaneous + '\'' +
                    ", orderFlags='" + orderFlags + '\'' +
                    "}";
        }
    }
}
