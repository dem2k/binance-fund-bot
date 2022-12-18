package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Result from getOrderBook
 *
 * @author Stéphane Bouclier
 */
public class OrderBookResult extends Result<Map<String, OrderBookResult.OrderBook>> {

    public static class OrderBook {
        public List<Market> asks;
        public List<Market> bids;

        @Override
        public String toString() {
            return "OrderBook{" +
                    "asks=" + asks +
                    ", bids=" + bids +
                    "}";
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonPropertyOrder({"price", "volume", "timestamp"})
    public static class Market {
        public BigDecimal price;
        public BigDecimal volume;
        public Long timestamp;

        private Market() {}

        public Market(BigDecimal price, BigDecimal volume, Long timestamp) {
            this.price = price;
            this.volume = volume;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Market{" +
                    "price=" + price +
                    ", volume=" + volume +
                    ", timestamp=" + timestamp +
                    "}";
        }
    }
}
