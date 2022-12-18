package dem2k;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StrategyMarket extends Strategy {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(StrategyMarket.class);

    private Long buyOrderRef;
    private BigDecimal transferAmount;

    public StrategyMarket(BuyerExchange kraken, SellerExchange binance, AppConfig config, AppUtils utils) {
        super(kraken, binance, config, utils);
    }

    @Override
    public void buy() throws Exception {
        BigDecimal xlm = kraken.baseAssetBalance();
        BigDecimal eur = kraken.quoteAssetBalance();
        LOG.info("BALANCE  {} {} + {} {}.", eur.toPlainString(), config.sourceAsset(), xlm.toPlainString(), config.baseAsset());

        BigDecimal price = kraken.bestAsk();
        BigDecimal amount = utils.maxTradingAmount(eur).divide(price, config.tickSizeFactor(), RoundingMode.DOWN);

        this.buyOrderRef = System.currentTimeMillis() / 1000;
        String txid = kraken.placeMarketOrder(amount, buyOrderRef);
        LOG.info("BUY      {} {} @ MARKET {} {}.", amount, config.baseAsset(), price, config.sourceAsset());
        this.transferAmount = xlm.add(amount);
    }

    @Override
    public Long buyOrderRef() {
        return buyOrderRef;
    }

    @Override
    public BigDecimal transferAmount() {
        return transferAmount;
    }

}
