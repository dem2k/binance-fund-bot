package com.github.sbouclier.result;

/**
 * Result from getServerTime
 *
 * @author Stéphane Bouclier
 */
public class ServerTimeResult extends Result<ServerTimeResult.ServerTime> {

    public static class ServerTime {
        public Long unixtime;
        public String rfc1123;

        @Override
        public String toString() {
            return "ServerTime{" +
                    "unixtime=" + unixtime +
                    ", rfc1123='" + rfc1123 + '\'' +
                    "}";
        }
    }
}
