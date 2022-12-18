package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * LedgersResultTest test
 *
 * @author Stéphane Bouclier
 */
public class LedgersResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/ledgers.mock.json");
        LedgersResult mockResult = new ObjectMapper().readValue(jsonResult, LedgersResult.class);

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={LKHYSJ-DXPLB-VDMZAL=LedgerInformation"));
    }
}
