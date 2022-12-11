package dem2k;

import java.math.BigDecimal;
import java.util.Set;

import com.github.sbouclier.KrakenApiException;
import com.github.sbouclier.KrakenApiRestClient;
import com.github.sbouclier.common.OrderDirection;
import com.github.sbouclier.result.AccountBalanceResult;
import com.github.sbouclier.result.AddOrderResult;
import com.github.sbouclier.result.ClosedOrdersResult;
import com.github.sbouclier.result.OpenOrdersResult;
import com.github.sbouclier.result.OrderBookResult;
import com.github.sbouclier.result.WithdrawResult;

public class BuyerExchangeKraken implements BuyerExchange {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(BuyerExchangeKraken.class);

    private final AppConfig config;
    private final AppUtils utils;
    private final KrakenApiRestClient kraken;

    private AccountBalanceResult accBalance;

    public BuyerExchangeKraken(KrakenApiRestClient client, AppConfig config, AppUtils utils) {
        this.config = config;
        this.utils = utils;
        this.kraken = client;
    }

    @Override
    public BigDecimal baseAssetBalance() throws KrakenApiException {
        if (accBalance == null) {
            accBalance = kraken.getAccountBalance();
        }
        return utils.getFromBalance(accBalance, config.baseAsset());
    }

    @Override
    public BigDecimal quoteAssetBalance() throws KrakenApiException {
        if (accBalance == null) {
            accBalance = kraken.getAccountBalance();
        }
        return utils.getFromBalance(accBalance, config.sourceAsset());
    }

    @Override
    public BigDecimal bestAsk() throws KrakenApiException {
        OrderBookResult result = kraken.getOrderBook(config.buyTicker(), 1);
        return result.getResult().get(config.ticketXZ()).asks.get(0).price;
    }

    @Override
    public BigDecimal bestBid() throws KrakenApiException {
        OrderBookResult result = kraken.getOrderBook(config.buyTicker(), 1);
        return result.getResult().get(config.ticketXZ()).bids.get(0).price;
    }

    @Override
    public String placeMarketOrder(BigDecimal amount, Long userRef) throws KrakenApiException {
        AddOrderResult result = kraken.addMarketOrder(OrderDirection.BUY, config.buyTicker(), amount, userRef);
        return result.getResult().txid.get(0);
    }

    @Override
    public String placeLimitOrder(BigDecimal amount, BigDecimal price, Long userRef) throws KrakenApiException {
        AddOrderResult result = kraken.addLimitOrder(OrderDirection.BUY, config.buyTicker(), amount, price, userRef);
        return result.getResult().txid.get(0);
    }

    @Override
    public Set<String> getOpenOrders() throws KrakenApiException {
        OpenOrdersResult result = kraken.getOpenOrders();
        return result.getResult().open.keySet();
    }

    @Override
    public String withdraw(BigDecimal amount) throws KrakenApiException {
        WithdrawResult withdraw = kraken.withdraw(config.baseAsset(), config.withdrawAdress(), amount.toString());
        return withdraw.getResult().referenceId;
    }

    @Override
    public ClosedOrdersResult.ClosedOrder getClosedOrderByRef(Long userRef) throws KrakenApiException {
        ClosedOrdersResult result = kraken.getClosedOrders(userRef);
        return result.getResult().closed.values().stream().findFirst().orElse(null);
    }

}
