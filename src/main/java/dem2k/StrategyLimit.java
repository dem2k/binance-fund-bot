package dem2k;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StrategyLimit extends Strategy {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(StrategyLimit.class);

    private Long buyOrderRef;
    private BigDecimal transferAmount;

    public StrategyLimit(BuyerExchange buyer, SellerExchange seller, AppConfig config, AppUtils utils) {
        super(buyer, seller, config, utils);
    }

    @Override
    public void buy() throws Exception {
        BigDecimal xlm = kraken.baseAssetBalance();
        BigDecimal eur = kraken.quoteAssetBalance();
        LOG.info("BALANCE  {} {} + {} {}", eur.toPlainString(), config.sourceAsset(), xlm.toPlainString(), config.baseAsset());

        BigDecimal price = kraken.bestBid();
        BigDecimal amount = utils.maxTradingAmount(eur).divide(price, config.tickSizeFactor(), RoundingMode.DOWN);

        this.buyOrderRef = System.currentTimeMillis() / 1000;
        String txid = kraken.placeLimitOrder(amount, price, buyOrderRef);
        LOG.info("BUY      {} {} @ LIMIT {} {}.", amount, config.baseAsset(), price, config.sourceAsset());
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
