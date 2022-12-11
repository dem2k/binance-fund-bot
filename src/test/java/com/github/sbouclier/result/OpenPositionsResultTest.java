package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * OpenPositionsResult test
 *
 * @author Stéphane Bouclier
 */
public class OpenPositionsResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/open_positions.mock.json");
        OpenPositionsResult mockResult = new ObjectMapper().readValue(jsonResult, OpenPositionsResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={TY3TFI-KXBN3-LEICZJ=OpenPosition"));
    }
}
