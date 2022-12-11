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
public class OrdersInformationResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/orders_information.mock.json");
        OrdersInformationResult mockResult = new ObjectMapper().readValue(jsonResult, OrdersInformationResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={OGRQC4-Q5C5N-2EYZDZ=OrderInfo"));
    }
}
