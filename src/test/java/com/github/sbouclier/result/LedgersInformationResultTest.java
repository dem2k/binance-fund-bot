package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * LedgersInformationResult test
 *
 * @author Stéphane Bouclier
 */
public class LedgersInformationResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/ledgers_information.mock.json");
        LedgersInformationResult mockResult = new ObjectMapper().readValue(jsonResult, LedgersInformationResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result=LedgersInformation"));
    }
}
