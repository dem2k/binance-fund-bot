package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Result from getRecentTrades
 *
 * @author Stéphane Bouclier
 */
public class RecentTradeResult extends ResultWithLastId<Map<String, List<RecentTradeResult.RecentTrade>>> {

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonPropertyOrder({"price", "volume", "time", "buySell", "marketLimit", "miscellaneous"})
    public static class RecentTrade {
        public BigDecimal price;
        public BigDecimal volume;
        public BigDecimal time;

        public Object buySell;
        public String marketLimit;
        public String miscellaneous;

        @Override
        public String toString() {
            return "RecentTrade{" +
                    "price=" + price +
                    ", volume=" + volume +
                    ", time=" + time +
                    ", buySell=" + buySell +
                    ", marketLimit='" + marketLimit + '\'' +
                    ", miscellaneous='" + miscellaneous + '\'' +
                    "}";
        }
    }
}
