package dem2k;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.github.sbouclier.KrakenApiRestClient;
import picocli.CommandLine;

public class AppMain {

    @CommandLine.Option(names = "-bek", description = "Buyer Exchange API Key", required = true)
    private String buyerExchangeApiKey;

    @CommandLine.Option(names = "-bes", description = "Buyer Exchange Secret", required = true)
    private String buyerExchangeSecret;

    @CommandLine.Option(names = "-sek", description = "Seller Exchange API Key", required = true)
    private String sellerExchangeApiKey;

    @CommandLine.Option(names = "-ses", description = "Seller Exchange Secret", required = true)
    private String sellerExchangeSecret;

    @CommandLine.Option(names = "-dst", description = "Destination Asset. Default: USDT")
    private String destAsset = "USDT";
    
    @CommandLine.Option(names = "-wa", description = "Withdrawal Adress Name. Default: binance-xlm")
    private String withdrawAdress = "binance-xlm";

    @CommandLine.Option(names = {"-?", "-h"}, description = "Display this Help Message", usageHelp = true)
    private static boolean usageHelpRequested = false;

    public static void main(String[] args) throws Exception {

        AppMain app = CommandLine.populateCommand(new AppMain(), args);

        if (usageHelpRequested) {
            CommandLine.usage(app, System.out);
            System.exit(1);
        }

        AppConfig config = AppConfig.builder()
                .buyerExchangeApiKey(app.buyerExchangeApiKey)
                .buyerExchangeSecret(app.buyerExchangeSecret)
                .sellerExchangeApiKey(app.sellerExchangeApiKey)
                .sellerExchangeSecret(app.sellerExchangeSecret)
                .destAsset(app.destAsset)
                .withdrawAdress(app.withdrawAdress)
                .build();

        System.out.println(config);

        AppUtils utils = new AppUtils(config);
        BuyerExchange buyerExchange =
               realKrakenBot(config, utils);
                // MockMocker.krakenBot(config, utils);
        SellerExchange sellerExchange =
               realBinanceBot(config, utils);
                // MockMocker.binanceBot(config, utils);
        Strategy strategy = new StrategyMarket(buyerExchange, sellerExchange, config, utils);
        AppController controller = new AppController(strategy);
        controller.start();
        System.exit(0);
    }

    private static BuyerExchange realKrakenBot(AppConfig config, AppUtils utils) {
        KrakenApiRestClient client = new KrakenApiRestClient(
                config.buyerExchangeApiKey(), config.buyerExchangeSecret());
        return new BuyerExchangeKraken(client, config, utils);
    }

    private static SellerExchange realBinanceBot(AppConfig config, AppUtils utils) {
        BinanceApiRestClient client = BinanceApiClientFactory.newInstance(
                config.sellerExchangeApiKey(), config.sellerExchangeSecret()).newRestClient();
        return new SellerExchangeBinance(client, config, utils);
    }

}
