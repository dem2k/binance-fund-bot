package dem2k;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppConfigTest {

    @Test
    public void testDefaults() {
        AppConfig config = AppConfig.builder().build();
        assertEquals(1500, config.sleepTime());
        assertEquals("XXLMZEUR", config.ticketXZ());
        assertEquals(4, config.tickSizeFactor());
        assertEquals("binance-xlm", config.withdrawAdress());
    }
    
}
