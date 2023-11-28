package dem2k;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.sbouclier.result.AccountBalanceResult;

public class AppUtils {

    private final AppConfig config;

    public AppUtils(AppConfig config) {
        this.config = config;
    }

    public BigDecimal maxTradingAmount(BigDecimal amount) {
        return amount.multiply(config.tradingAmountFactor()).setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal getFromBalance(AccountBalanceResult balance, String coin) {
        String kName = balance.getResult().keySet().stream()
                .filter(key -> mayStartWithZX(key, coin)).findFirst().orElse(null);
        if (kName == null) {
            return new BigDecimal("0.00");
        }
        return balance.getResult().get(kName);
    }

    private boolean mayStartWithZX(String s, String coin) {
        return s.equals(coin) || s.equals("Z" + coin) || s.equals("X" + coin);
    }

    public BigDecimal truncateLotSize(BigDecimal amount) {
        return switch (config.lotStepSize().toString()) {
            case "1" -> amount.setScale(0, RoundingMode.DOWN);
            case "0.1" -> amount.setScale(1, RoundingMode.DOWN);
            case "0.01" -> amount.setScale(2, RoundingMode.DOWN);
            case "0.001" -> amount.setScale(3, RoundingMode.DOWN);
            default -> throw new RuntimeException("LOT_SIZE_NOT_IMPLEMENTED");
        };
    }

}
