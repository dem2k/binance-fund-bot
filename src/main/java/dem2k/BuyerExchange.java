package dem2k;

import java.math.BigDecimal;
import java.util.Set;

import com.github.sbouclier.KrakenApiException;
import com.github.sbouclier.result.ClosedOrdersResult;

public interface BuyerExchange {
    
    BigDecimal baseAssetBalance() throws Exception;

    BigDecimal quoteAssetBalance() throws Exception;

    BigDecimal bestAsk() throws Exception;

    BigDecimal bestBid() throws Exception;

    String placeLimitOrder(BigDecimal amount, BigDecimal price, Long userRef) throws Exception;

    String placeMarketOrder(BigDecimal amount, Long userRef) throws Exception;

    Set<String> getOpenOrders() throws Exception;

    String withdraw(BigDecimal amount) throws Exception;

    ClosedOrdersResult.ClosedOrder getClosedOrderByRef(Long userRef) throws Exception;

}
