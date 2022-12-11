package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * ClosedOrdersResult test
 *
 * @author Stéphane Bouclier
 */
public class ClosedOrdersResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/closed_orders.mock.json");
        ClosedOrdersResult mockResult = new ObjectMapper().readValue(jsonResult, ClosedOrdersResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result=ClosedOrders"));
    }
}
