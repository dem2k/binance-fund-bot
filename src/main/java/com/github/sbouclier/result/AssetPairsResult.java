package com.github.sbouclier.result;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Result from getAssetPairs
 *
 * @author Stéphane Bouclier
 */
public class AssetPairsResult extends Result<Map<String, AssetPairsResult.AssetPair>> {

    public static class AssetPair {

        @JsonFormat(shape = JsonFormat.Shape.ARRAY)
        @JsonPropertyOrder({"volume", "percent"})
        public static class Fee {

            public Integer volume;
            public Float percent;

            private Fee() {
            }

            public Fee(Integer volume, Float percent) {
                this.volume = volume;
                this.percent = percent;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Fee)) {
                    return false;
                }
                Fee fee = (Fee) o;
                return volume.equals(fee.volume) && percent.equals(fee.percent);
            }

            @Override
            public int hashCode() {
                return Objects.hash(volume, percent);
            }

            @Override
            public String toString() {
                return "Fee{" +
                        "volume=" + volume +
                        ", percent=" + percent +
                        '}';
            }
        }

        @JsonProperty("altname")
        public String alternatePairName;

        @JsonProperty("aclass_base")
        public String baseAssetClass;

        @JsonProperty("base")
        public String baseAssetId;

        @JsonProperty("aclass_quote")
        public String quoteAssetClass;

        @JsonProperty("quote")
        public String quoteAssetId;

        public String lot;

        @JsonProperty("pair_decimals")
        public Integer pairDecimals;

        @JsonProperty("lot_decimals")
        public Integer lotDecimals;

        @JsonProperty("lot_multiplier")
        public Integer lotMultiplier;

        @JsonProperty("leverage_buy")
        public ArrayList<Integer> leverageBuy;

        @JsonProperty("leverage_sell")
        public ArrayList<Integer> leverageSell;

        public ArrayList<Fee> fees;

        @JsonProperty("fees_maker")
        public ArrayList<Fee> feesMaker;

        @JsonProperty("fee_volume_currency")
        public String feeVolumeCurrency;

        @JsonProperty("margin_call")
        public Integer marginCall;

        @JsonProperty("margin_level")
        public Integer marginLevel;

        @JsonProperty("margin_stop")
        public Integer marginStop;

        @Override
        public String toString() {
            return "AssetPair{" +
                    "alternatePairName='" + alternatePairName + '\'' +
                    ", baseAssetClass='" + baseAssetClass + '\'' +
                    ", baseAssetId='" + baseAssetId + '\'' +
                    ", quoteAssetClass='" + quoteAssetClass + '\'' +
                    ", quoteAssetId='" + quoteAssetId + '\'' +
                    ", lot='" + lot + '\'' +
                    ", pairDecimals=" + pairDecimals +
                    ", lotDecimals=" + lotDecimals +
                    ", lotMultiplier=" + lotMultiplier +
                    ", leverageBuy=" + leverageBuy +
                    ", leverageSell=" + leverageSell +
                    ", fees=" + fees +
                    ", feesMaker=" + feesMaker +
                    ", feeVolumeCurrency='" + feeVolumeCurrency + '\'' +
                    ", marginCall=" + marginCall +
                    ", marginLevel=" + marginLevel +
                    ", marginStop=" + marginStop +
                    '}';
        }
    }
}
