package com.github.sbouclier.result;

import java.math.BigDecimal;

/**
 * Result from getTradeVolume
 *
 * @author Stéphane Bouclier
 */
public class TradeVolumeResult extends Result<TradeVolumeResult.TradeVolume> {

    public static class TradeVolume {

        public String currency;
        public BigDecimal volume;

        @Override
        public String toString() {
            return "TradeVolume{" +
                    "currency='" + currency + '\'' +
                    ", volume=" + volume +
                    "}";
        }
    }
}
