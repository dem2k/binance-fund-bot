package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.github.sbouclier.mock.MockInitHelper;

/**
 * RecentTradeResultTest test
 *
 * @author Stéphane Bouclier
 */
public class RecentTradeResultTest {

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        RecentTradeResult mockResult = MockInitHelper.buildRecentTradeResult();

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={XXBTZEUR=[RecentTrade"));
    }
}
