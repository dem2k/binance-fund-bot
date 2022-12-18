package dem2k;

import java.math.BigDecimal;

import com.binance.api.client.domain.account.NewOrderResponse;

public interface SellerExchange {

    BigDecimal getBaseCurrencyAmount();

    NewOrderResponse sellByMarket(BigDecimal amount);
    
}
