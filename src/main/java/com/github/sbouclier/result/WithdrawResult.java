package com.github.sbouclier.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawResult extends Result<WithdrawResult.Withdraw> {

    public static class Withdraw {
        @JsonProperty("refid")
        public String referenceId;

        @Override
        public String toString() {
            return "Withdraw{" +
                    "referenceId='" + referenceId + '\'' +
                    "}";
        }
    }
}
