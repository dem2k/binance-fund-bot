package dem2k;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.sbouclier.result.AccountBalanceResult;

public class AppUtilsTest {

    private AppUtils utils = new AppUtils(AppConfig.builder().build());

    @Test
    public void get_quote_balance() {
        // {"error":[],"result":{"TRX":"0.00000000","DOT.S":"0.0000000000","ADA":"0.00000004","ATOM":"0.00000000","XXBT":"0.0000000000","XXLM":"0.00000723","ATOM.S":"0.00000000","ZEUR":"16.0795","DOT":"0.0059127100","XXRP":"0.00000009","XETH":"0.0000000000"}}
        //Result{error=[], result={TRX=0E-8, DOT.S=0E-10, ADA=4E-8, ATOM=0E-8, XXBT=0E-10, XXLM=0.00000723, ATOM.S=0E-8, ZEUR=16.0795, DOT=0.0059127100, XXRP=9E-8, XETH=0E-10}}
        AccountBalanceResult result = new AccountBalanceResult();
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("TRX", new BigDecimal("0.00000000"));
        map.put("ZEUR", new BigDecimal("16.0795"));
        map.put("XXLM", new BigDecimal("0.00000723"));
        map.put("XXRP", new BigDecimal("0.00000009"));
        result.setResult(map);
        BigDecimal eur = utils.getFromBalance(result, "EUR");
        assertEquals(new BigDecimal("16.0795"), eur);
    }

    @Test
    public void get_base_balance() {
        // {"error":[],"result":{"TRX":"0.00000000","DOT.S":"0.0000000000","ADA":"0.00000004","ATOM":"0.00000000","XXBT":"0.0000000000","XXLM":"0.00000723","ATOM.S":"0.00000000","ZEUR":"16.0795","DOT":"0.0059127100","XXRP":"0.00000009","XETH":"0.0000000000"}}
        //Result{error=[], result={TRX=0E-8, DOT.S=0E-10, ADA=4E-8, ATOM=0E-8, XXBT=0E-10, XXLM=0.00000723, ATOM.S=0E-8, ZEUR=16.0795, DOT=0.0059127100, XXRP=9E-8, XETH=0E-10}}
        AccountBalanceResult result = new AccountBalanceResult();
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("TRX", new BigDecimal("0.00000000"));
        map.put("ZEUR", new BigDecimal("16.0795"));
        map.put("XXLM", new BigDecimal("0.00000723"));
        map.put("XXRP", new BigDecimal("0.00000009"));
        result.setResult(map);
        BigDecimal eur = utils.getFromBalance(result, "XLM");
        assertEquals(new BigDecimal("0.00000723"), eur);
    }

    @Test
    public void get_max_reserver() {
        BigDecimal amount = new BigDecimal("1000.00");
        BigDecimal result = utils.maxTradingAmount(amount);
        assertEquals("995.00", result.toString());
    }

    @Test
    public void truncate_lot_size_1() {
        BigDecimal amount = new BigDecimal("1234.56789");
        BigDecimal result = utils.truncateLotSize(amount);
        assertEquals("1234", result.toString());
    }

}
