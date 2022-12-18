package com.github.sbouclier.result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Result from getOHLC
 *
 * @author Stéphane Bouclier
 */
public class OHLCResult extends ResultWithLastId<Map<String, List<OHLCResult.OHLC>>> {

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonPropertyOrder({"time", "open", "high", "low", "close", "vwap", "volume", "count"})
    public static class OHLC {
        public Integer time;
        public BigDecimal open;
        public BigDecimal high;
        public BigDecimal low;
        public BigDecimal close;
        public BigDecimal vwap;
        public BigDecimal volume;
        public Integer count;

        @Override
        public String toString() {
            return "OHLC{" +
                    "time=" + time +
                    ", open=" + open +
                    ", high=" + high +
                    ", low=" + low +
                    ", close=" + close +
                    ", vwap=" + vwap +
                    ", volume=" + volume +
                    ", count=" + count +
                    '}';
        }
    }
}
