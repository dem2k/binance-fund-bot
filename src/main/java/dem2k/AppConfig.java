package dem2k;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AppConfig {

    @Builder.Default
    private BigDecimal lotStepSize = new BigDecimal("1");

    @Builder.Default
    private BigDecimal tradingAmountFactor = new BigDecimal("0.995");

    @Builder.Default
    private BigDecimal tickSize = new BigDecimal("0.0001");

    @Builder.Default
    private String baseAsset = "XLM";

    @Builder.Default
    private String sourceAsset = "EUR";

    @Builder.Default
    private String destAsset = "USDT";

    @Builder.Default
    private String withdrawAdress = "binance-xlm";
    
    @Builder.Default
    private long sleepTime = 2500;

    private String buyerExchangeApiKey;
    private String buyerExchangeSecret;
    private String sellerExchangeApiKey;
    private String sellerExchangeSecret;

    public String buyTicker() {
        return baseAsset + sourceAsset;
    }

    public String sellTicker() {
        return baseAsset + destAsset;
    }

    public String ticketXZ() {
        return "X" + baseAsset + "Z" + sourceAsset;
    }

    public int tickSizeFactor() {
        int result = 0;
        BigDecimal tick = new BigDecimal(tickSize.toString());
        while (tick.compareTo(BigDecimal.ONE) < 0) {
            result++;
            tick = tick.multiply(BigDecimal.TEN);
        }
        return result;
    }

}

