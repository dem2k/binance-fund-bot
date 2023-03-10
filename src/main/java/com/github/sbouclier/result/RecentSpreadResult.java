package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Result from getRecentSpread
 *
 * @author Stéphane Bouclier
 */
public class RecentSpreadResult extends ResultWithLastId<Map<String, List<RecentSpreadResult.Spread>>> {

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonPropertyOrder({"time", "bid", "ask"})
    public static class Spread {
        public Integer time;
        public BigDecimal bid;
        public BigDecimal ask;

        private Spread() {}

        public Spread(Integer time, BigDecimal bid, BigDecimal ask) {
            this.time = time;
            this.bid = bid;
            this.ask = ask;
        }

        @Override
        public String toString() {
            return "Spread{" +
                    "time=" + time +
                    ", bid=" + bid +
                    ", ask=" + ask +
                    "}";
        }
    }
}
