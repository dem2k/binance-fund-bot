package com.github.sbouclier.result;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sbouclier.common.LedgerInformation;

/**
 * Result from getLedgersInformation
 *
 * @author Stéphane Bouclier
 */
public class LedgersInformationResult extends Result<LedgersInformationResult.LedgersInformation> {

    public static class LedgersInformation {

        @JsonProperty("ledger")
        public Map<String, LedgerInformation> ledger;

        public Long count;

        @Override
        public String toString() {
            return "LedgersInformation{" +
                    "ledger=" + ledger +
                    ", count=" + count +
                    '}';
        }
    }
}
