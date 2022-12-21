package dem2k;

import static com.binance.api.client.domain.account.NewOrder.limitSell;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@Slf4j
public class ConvertAllBusdToUsdtByLimitOrder {

    @CommandLine.Option(names = "-bek", description = "Buyer Exchange API Key", required = true)
    private String buyerExchangeApiKey;

    @CommandLine.Option(names = "-bes", description = "Buyer Exchange Secret", required = true)
    private String buyerExchangeSecret;

    @CommandLine.Option(names = "-sek", description = "Seller Exchange API Key", required = true)
    private String sellerExchangeApiKey;

    @CommandLine.Option(names = "-ses", description = "Seller Exchange Secret", required = true)
    private String sellerExchangeSecret;

    @CommandLine.Option(names = {"-?", "-h"}, description = "Display this Help Message", usageHelp = true)
    private static boolean usageHelpRequested = false;

    public static void main(String[] args) throws Exception {

        ConvertAllBusdToUsdtByLimitOrder app =
                CommandLine.populateCommand(new ConvertAllBusdToUsdtByLimitOrder(), args);

        if (usageHelpRequested) {
            CommandLine.usage(app, System.out);
            System.exit(1);
        }

        AppConfig config = AppConfig.builder()
                .buyerExchangeApiKey(app.buyerExchangeApiKey)
                .buyerExchangeSecret(app.buyerExchangeSecret)
                .sellerExchangeApiKey(app.sellerExchangeApiKey)
                .sellerExchangeSecret(app.sellerExchangeSecret)
                .build();

        app.process(config);

        Thread.sleep(10_000);
        System.exit(0);
    }

    public void process(AppConfig config) {
        BinanceApiRestClient binance = BinanceApiClientFactory.newInstance(
                config.sellerExchangeApiKey(), config.sellerExchangeSecret()).newRestClient();

        String busd = new BigDecimal(binance.getAccount().getAssetBalance("BUSD").getFree())
                .setScale(0, RoundingMode.DOWN).toPlainString();

        OrderBook book = binance.getOrderBook("BUSDUSDT", 1);
        OrderBookEntry bid = book.getBids().get(0);
        BigDecimal price = new BigDecimal(bid.getPrice()).max(new BigDecimal("1.0000"));

        log.info("Placing LIMIT {} x {} // {} \n",
                busd, price.toPlainString(), bid);
        System.console().readLine("Press ENTER to continue...");

        NewOrderResponse response = binance.newOrder(limitSell(
                "BUSDUSDT", TimeInForce.GTC, busd, price.toPlainString()));
        log.info("{}", response);
    }

}
