package dem2k;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.binance.api.client.domain.account.NewOrderResponse;
import com.github.sbouclier.result.ClosedOrdersResult;

public abstract class Strategy {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(Strategy.class);

    protected final BuyerExchange kraken;
    protected final SellerExchange binance;
    protected final AppConfig config;
    protected final AppUtils utils;

    public Strategy(BuyerExchange kraken, SellerExchange binance, AppConfig config, AppUtils utils) {
        this.config = config;
        this.utils = utils;
        this.kraken = kraken;
        this.binance = binance;
    }

    abstract void buy() throws Exception;

    public void transfer() throws Exception {
        String txid = kraken.withdraw(transferAmount());
        LOG.info("TRANSFER {} {} to Binance.", transferAmount(), config.baseAsset());
    }

    public void sell() {
        NewOrderResponse response = binance.sellByMarket(amountToSell());
        String price = response.getPrice();
        if (new BigDecimal(price).compareTo(BigDecimal.ZERO) == 0) {
            price = "PRICE";
        }
        LOG.info("SELL     {} {} @ {} {} {}.",
                response.getExecutedQty(), response.getSymbol(), response.getType(), price, response.getStatus());
    }

    public void waitForOrderExecution() throws Exception {
        System.out.print("Waiting for Order execution...");
        do {
            sleep();
        } while (orderPending());
        System.out.println();
    }

    private boolean orderPending() throws Exception {
        if (buyOrderRef() == null) {
            throw new IllegalStateException("Buy Order Reference not initialized.");
        }

        ClosedOrdersResult.ClosedOrder order = kraken.getClosedOrderByRef(buyOrderRef());
        if (order == null) {
            return true;
        }
        if (order.status == ClosedOrdersResult.ClosedOrder.Status.CANCELED) {
            System.out.println();
            LOG.info("EXIT     Order canceled.");
            System.exit(1);
        }
        return order.status != ClosedOrdersResult.ClosedOrder.Status.CLOSED;
    }

    public void waitForTransfer() {
        System.out.print("Waiting for Amount transfer...");
        do {
            sleep();
        } while (transferPending());
        System.out.println();
    }

    private boolean transferPending() {
        return binance.getBaseCurrencyAmount().compareTo(amountToSell()) < 0;
    }

    private BigDecimal amountToSell() {
        return transferAmount()
                .multiply(new BigDecimal("0.9999")) //todo: move this constant to config
                .setScale(config.tickSizeFactor(), RoundingMode.DOWN);
    }

    public abstract Long buyOrderRef();

    public abstract BigDecimal transferAmount();

    public void sleep() {
        try {
            System.out.print(".");
            Thread.sleep(config.sleepTime());
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
