package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import java.io.IOException;

import org.junit.Test;

import com.github.sbouclier.mock.MockInitHelper;

/**
 * OHLCResult test
 *
 * @author Stéphane Bouclier
 */
public class OHLCResultTest {

    @Test
    public void should_return_to_string() throws IOException {
        // Given
        OHLCResult mockResult = MockInitHelper.buildOHLCResult();
        // When
        String toString = mockResult.toString();
        // Then
        assertThat(toString, containsString("result={XXBTZEUR=[OHLC"));
    }
}
