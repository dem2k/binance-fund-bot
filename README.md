# binance-fund-bot

Buy crypto on Kraken, send the coins to Binance and sell them there.

# installation

````
scoop bucket add dem2k https://github.com/dem2k/scoop-bucket
scoop install binance-fund-bot
````

On Kraken you need to create a new Withdrawal-Address and name it "binance-xlm". Your Coins will be sent to this Address.

# usage

````
binance-fund-bot.bat -?

binance-fund-bot.bat -bek=%BUYER_API_KEY% -bes=%BUYER_SECRET% -sek=%SELLER_API_KEY% -ses=%SELLER_SECRET%
````

This buys XLM for all your EUR on Kraken, sends it to Binance and sells it there against USDT. If you already have enough XLM on Binace, the sale will be made immediately without waiting for the coins to actually arrive.
