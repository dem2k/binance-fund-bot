package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * OpenOrdersResult test
 *
 * @author Stéphane Bouclier
 */
public class OpenOrdersResultTest {

    @Test
    public void should_return_to_string() throws IOException {
        // Given
        String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/open_orders.mock.json");
        OpenOrdersResult mockResult = new ObjectMapper().readValue(jsonResult, OpenOrdersResult.class);
        // When
        String toString = mockResult.toString();
        // Then
        assertThat(toString, StringContains.containsString("result=OpenOrders"));
    }
}
