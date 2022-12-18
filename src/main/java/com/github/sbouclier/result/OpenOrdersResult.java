package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.sbouclier.common.OrderDirection;
import com.github.sbouclier.common.OrderType;

/**
 * Result from getOpenOrders
 *
 * @author Stéphane Bouclier
 */
public class OpenOrdersResult extends Result<OpenOrdersResult.OpenOrders> {

    public static class OpenOrders {

        @JsonProperty("open")
        public Map<String, OpenOrdersResult.OpenOrder> open;

        @Override
        public String toString() {
            return "OpenOrders{" +
                    "open=" + open +
                    "}";
        }
    }

    public static class OpenOrder {

        public enum Status {
            PENDING("pending"),
            OPEN("open"),
            CLOSED("closed"),
            CANCELED("canceled"),
            EXPIRED("expired");

            private String value;

            Status(String value) {
                this.value = value;
            }

            @JsonValue
            public String getValue() {
                return value;
            }
        }

        public static class Description {

            @JsonProperty("pair")
            public String assetPair;

            @JsonProperty("type")
            public OrderDirection orderDirection;

            @JsonProperty("ordertype")
            public OrderType orderType;

            public BigDecimal price;

            @JsonProperty("price2")
            public BigDecimal secondaryPrice;

            public String leverage;

            public String order;

            public String close;

            @Override
            public String toString() {
                return "Description{" +
                        "assetPair='" + assetPair + '\'' +
                        ", orderDirection=" + orderDirection +
                        ", orderType=" + orderType +
                        ", price=" + price +
                        ", secondaryPrice=" + secondaryPrice +
                        ", leverage='" + leverage + '\'' +
                        ", order='" + order + '\'' +
                        ", close='" + close + '\'' +
                        '}';
            }

        }

        @JsonProperty("refid")
        public String referralOrderTransactionId;

        @JsonProperty("userref")
        public String userReferenceId;

        public Status status;

        @JsonProperty("opentm")
        public Long openTimestamp;

        @JsonProperty("starttm")
        public Long orderStartTimestamp;

        @JsonProperty("expiretm")
        public Long orderEndTimestamp;

        @JsonProperty("descr")
        public Description description;

        @JsonProperty("vol")
        public BigDecimal volumeOrder;

        @JsonProperty("vol_exec")
        public BigDecimal volumeExecuted;

        public BigDecimal cost;

        public BigDecimal fee;

        @JsonProperty("price")
        public BigDecimal averagePrice;

        @JsonProperty("stopprice")
        public BigDecimal stopPrice;

        @JsonProperty("limitprice")
        public BigDecimal limitPrice;

        @Override
        public String toString() {
            return "OpenOrder{" +
                    "referralOrderTransactionId='" + referralOrderTransactionId + '\'' +
                    ", userReferenceId='" + userReferenceId + '\'' +
                    ", status=" + status +
                    ", openTimestamp=" + openTimestamp +
                    ", orderStartTimestamp=" + orderStartTimestamp +
                    ", orderEndTimestamp=" + orderEndTimestamp +
                    ", description=" + description +
                    ", volumeOrder=" + volumeOrder +
                    ", volumeExecuted=" + volumeExecuted +
                    ", cost=" + cost +
                    ", fee=" + fee +
                    ", averagePrice=" + averagePrice +
                    ", stopPrice=" + stopPrice +
                    ", limitPrice=" + limitPrice +
                    ", miscellaneous='" + miscellaneous + '\'' +
                    ", orderFlags='" + orderFlags + '\'' +
                    '}';
        }

        @JsonProperty("misc")
        public String miscellaneous;

        @JsonProperty("oflags")
        public String orderFlags;

    }
}
