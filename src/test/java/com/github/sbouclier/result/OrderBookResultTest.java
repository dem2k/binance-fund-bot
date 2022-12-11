package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.IOException;
import java.math.BigDecimal;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * OrderBookResult test
 *
 * @author Stéphane Bouclier
 */
public class OrderBookResultTest {

    @Test
    public void should_construct_market() {
        OrderBookResult.Market market = new OrderBookResult.Market(BigDecimal.TEN, BigDecimal.ONE, 5000L);

        assertThat(market.price, equalTo(BigDecimal.TEN));
        assertThat(market.volume, equalTo(BigDecimal.ONE));
        assertThat(market.timestamp, equalTo(5000L));
    }

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/order_book.mock.json");
        OrderBookResult mockResult = new ObjectMapper().readValue(jsonResult, OrderBookResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={XXBTZEUR=OrderBook"));
    }
}
