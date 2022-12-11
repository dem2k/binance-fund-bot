package dem2k;

import static com.binance.api.client.domain.account.NewOrder.marketSell;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;

public class SellerExchangeBinance implements SellerExchange {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(SellerExchangeBinance.class);

    private final AppConfig config;
    private final AppUtils utils;
    private final BinanceApiRestClient binance;

    public SellerExchangeBinance(BinanceApiRestClient client, AppConfig config, AppUtils utils) {
        this.config = config;
        this.utils = utils;
        this.binance = client;
    }

    @Override
    public BigDecimal getBaseCurrencyAmount() {
        String amount = binance.getAccount().getAssetBalance(config.baseAsset()).getFree();
        return new BigDecimal(amount).setScale(config.tickSizeFactor(), RoundingMode.DOWN);
    }

    @Override
    public NewOrderResponse sellByMarket(BigDecimal amount) {
        BigDecimal tosell = utils.truncateLotSize(amount);
        NewOrder order = marketSell(config.sellTicker(), tosell.toString());
        LOG.debug(order.toString());
        NewOrderResponse response = binance.newOrder(order);
        LOG.debug(response.toString());
        return response;
    }

}
