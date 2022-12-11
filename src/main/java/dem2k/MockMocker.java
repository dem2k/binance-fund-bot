package dem2k;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.KrakenApiRestClient;
import com.github.sbouclier.common.OrderDirection;
import com.github.sbouclier.result.AccountBalanceResult;
import com.github.sbouclier.result.AddOrderResult;
import com.github.sbouclier.result.ClosedOrdersResult;
import com.github.sbouclier.result.OrderBookResult;
import com.github.sbouclier.result.WithdrawResult;

public class MockMocker {

    static BuyerExchange krakenBot(AppConfig config, AppUtils utils) throws Exception {
        KrakenApiRestClient client = mock(KrakenApiRestClient.class);
        when(client.getAccountBalance()).thenReturn(balance());
        when(client.getOrderBook("XLMEUR", 1)).thenReturn(orderBook());
        when(client.addLimitOrder(any(OrderDirection.class), anyString(), any(BigDecimal.class), any(BigDecimal.class), anyLong())).thenReturn(limitOrder());
        when(client.addMarketOrder(any(OrderDirection.class), anyString(), any(BigDecimal.class), anyLong())).thenReturn(marketOrder());
        when(client.getClosedOrders(anyLong())).thenReturn(closedOrders());
        when(client.withdraw(eq("XLM"), anyString(), anyString())).thenReturn(withdraw());
        return new BuyerExchangeKraken(client, config, utils);
    }

    public static SellerExchange binanceBot(AppConfig config, AppUtils utils) throws Exception {
        BinanceApiRestClient client = mock(BinanceApiRestClient.class);
        when(client.getAccount()).thenReturn(binanceAccount());
        when(client.newOrder(any(NewOrder.class))).thenReturn(binanceNewOrder(config.sellTicker()));
        return new SellerExchangeBinance(client, config, utils);
    }

    private static AddOrderResult limitOrder() throws Exception {
        // {"error":[],"result":{"txid":["ORDWGJ-P6DML-O732XS"],"descr":{"order":"buy 133.11000000 XLMEUR @ limit 0.101122"}}}
        return new ObjectMapper()
                .readValue("{\"error\":[],\"result\":{\"txid\":[\"ORDWGJ-P6DML-O732XS\"],\"descr\":{\"order\":\"buy 183.77800000 XLMEUR @ limit 0.11410500\"}}}",
                        AddOrderResult.class);
    }

    private static NewOrderResponse binanceNewOrder(String ticker) throws Exception {
        //{"symbol": "BTCUSDT", "orderId": 28, "orderListId": -1, "clientOrderId": "6gCrw2kRUAF9CvJDGP16IP", "transactTime": 1507725176595, "price": "0.00000000", "origQty": "10.00000000", "executedQty": "10.00000000", "cummulativeQuoteQty": "10.00000000", "status": "FILLED", "timeInForce": "GTC", "type": "MARKET", "side": "SELL", "strategyId": 1,"strategyType": 1000000}
        return new ObjectMapper()
                .readValue("{\"symbol\": \"" + ticker + "\", \"orderId\": 28, \"orderListId\": -1, \"clientOrderId\": \"6gCrw2kRUAF9CvJDGP16IP\", \"transactTime\": 1507725176595, \"price\": \"0.00000000\", \"origQty\": \"195.00000000\", \"executedQty\": \"195.00000000\", \"cummulativeQuoteQty\": \"195.00000000\", \"status\": \"FILLED\", \"timeInForce\": \"GTC\", \"type\": \"MARKET\", \"side\": \"SELL\", \"strategyId\": 1,\"strategyType\": 1000000}",
                        NewOrderResponse.class);
    }

    private static Account binanceAccount() throws Exception {
        //{"makerCommission": 15, "takerCommission": 15, "buyerCommission": 0, "sellerCommission": 0, "canTrade": true, "canWithdraw": true, "canDeposit": true, "brokered":false, "updateTime": 123456789, "accountType": "SPOT", "balances": [{"asset": "BTC", "free": "4723846.89208129", "locked": "0.00000000"},{"asset": "LTC", "free": "4763368.68006011", "locked": "0.00000000"}],"permissions": ["SPOT"]}
        return new ObjectMapper()
                .readValue("{\"makerCommission\": 15, \"takerCommission\": 15, \"buyerCommission\": 0, \"sellerCommission\": 0, \"canTrade\": true, \"canWithdraw\": true, \"canDeposit\": true, \"brokered\":false, \"updateTime\": 123456789, \"accountType\": \"SPOT\", \"balances\": [{\"asset\": \"BTC\", \"free\": \"4723846.89208129\", \"locked\": \"0.00000000\"},{\"asset\": \"XLM\", \"free\": \"195.8419\", \"locked\": \"0.00000000\"}],\"permissions\": [\"SPOT\"]}",
                        Account.class);
    }

    private static WithdrawResult withdraw() throws Exception {
        // // {"error":[],"result":{"refid":"AUBVFOW-BTYE3U-U6CGQM"}}
        return new ObjectMapper()
                .readValue("{ \"error\":[], \"result\":{ \"refid\":\"AUBVFOW-BTYE3U-U6CGQM\" }}",
                        WithdrawResult.class);
    }

    private static ClosedOrdersResult closedOrders() throws Exception {
        // {"result":{"closed":{"OQR6ZR-EGNQD-GV4TKZ": {        "refid": null,        "userref": 0,        "status": "closed",        "opentm": 1659830971.5500839,        "starttm": 0,        "expiretm": 0,        "descr": {          "pair": "XLMEUR",          "type": "buy",          "ordertype": "limit",          "price": "11949",          "price2": "0",          "leverage": "none",          "order": "buy 125.36610000 XLMEUR @ limit 0.119490",          "close": ""        },        "vol": "125.36610000",        "vol_exec": "125.36610000",        "cost": "14.97598357",        "fee": "0.03893756",        "price": "119457",        "stopprice": "0.00000000",        "limitprice": "0.00000000",        "misc": "",        "oflags": "fciq",        "reason": null,        "closetm": 1659830971.55118      }    },    "count": 1  }}
        return new ObjectMapper()
                .readValue("{  \"result\": {    \"closed\": {      \"OQR6ZR-EGNQD-GV4TKZ\": {        \"refid\": null,        \"userref\": 0,        \"status\": \"closed\",        \"opentm\": 1659830971.5500839,        \"starttm\": 0,        \"expiretm\": 0,        \"descr\": {          \"pair\": \"XLMEUR\",          \"type\": \"buy\",          \"ordertype\": \"limit\",          \"price\": \"11949\",          \"price2\": \"0\",          \"leverage\": \"none\",          \"order\": \"buy 125.36610000 XLMEUR @ limit 0.119490\",          \"close\": \"\"        },        \"vol\": \"125.36610000\",        \"vol_exec\": \"125.36610000\",        \"cost\": \"14.97598357\",        \"fee\": \"0.03893756\",        \"price\": \"119457\",        \"stopprice\": \"0.00000000\",        \"limitprice\": \"0.00000000\",        \"misc\": \"\",        \"oflags\": \"fciq\",        \"reason\": null,        \"closetm\": 1659830971.55118      }    },    \"count\": 1  }}",
                        ClosedOrdersResult.class);
    }

    private static AddOrderResult marketOrder() throws Exception {
        // {"error": [],"result":{"descr":{"order":"buy 2.12340000 XLMEUR @ limit 45000.11"},"txid":["OUF4EM-FRGI2-MQMWZD"]}}
        return new ObjectMapper()
                .readValue("{\"error\": [],\"result\":{\"descr\":{\"order\":\"buy 2.12340000 XLMEUR @ limit 45000.11\"},\"txid\":[\"OUF4EM-FRGI2-MQMWZD\"]}}",
                        AddOrderResult.class);
    }

    private static OrderBookResult orderBook() throws Exception {
        //{"error":[],"result":{"XXLMZEUR":{"asks":[["0.11426800","13767.218",1659130937]],"bids":[["0.11410500","1600.000",1659130886]]}}}
        return new ObjectMapper()
                .readValue("{\"error\":[],\"result\":{\"XXLMZEUR\":{\"asks\":[[\"0.11426800\",\"13767.218\",1659130937]],\"bids\":[[\"0.11426800\",\"1600.000\",1659130886]]}}}",
                        OrderBookResult.class);
    }

    private static AccountBalanceResult balance() throws Exception {
        // {"error":[],"result":{"XXLM":"0.00000723","ZEUR":"16.0795"}}
        return new ObjectMapper()
                .readValue("{\"error\":[],\"result\":{\"XXLM\":\"12.3456789\",\"ZEUR\":\"21.0795\"}}",
                        AccountBalanceResult.class);
    }
}
