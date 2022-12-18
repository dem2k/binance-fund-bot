package com.github.sbouclier.result;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Result from getAssetsInformation
 *
 * @author Stéphane Bouclier
 */
public class AssetsInformationResult extends Result<Map<String, AssetsInformationResult.AssetInformation>> {

    public static class AssetInformation {

        @JsonProperty("altname")
        public String alternateName;

        @JsonProperty("aclass")
        public String assetClass;

        public Byte decimals;

        @JsonProperty("display_decimals")
        public Byte displayDecimals;

        @Override
        public String toString() {
            return "AssetInformation{" +
                    "alternateName='" + alternateName + '\'' +
                    ", assetClass='" + assetClass + '\'' +
                    ", decimals=" + decimals +
                    ", displayDecimals=" + displayDecimals +
                    '}';
        }
    }
}
