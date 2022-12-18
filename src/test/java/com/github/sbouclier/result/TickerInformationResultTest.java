package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * TickerInformationResult test
 *
 * @author Stéphane Bouclier
 */
public class TickerInformationResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/ticker_information.mock.json");
        TickerInformationResult mockResult = new ObjectMapper().readValue(jsonResult, TickerInformationResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={XETHZEUR=TickerInformation"));
    }
}
