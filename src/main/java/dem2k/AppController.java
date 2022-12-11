package dem2k;

import com.github.sbouclier.KrakenApiException;

public class AppController {

    private static final org.slf4j.Logger LOG =
            org.slf4j.LoggerFactory.getLogger(AppController.class);

    private final Strategy strategy;

    public AppController(Strategy strategy) {
        this.strategy = strategy;
    }

    public void start() throws Exception {
        strategy.buy();
        strategy.waitForOrderExecution();
        strategy.transfer();
        strategy.waitForTransfer();
        strategy.sell();
    }

}
