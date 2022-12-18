package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.github.sbouclier.mock.MockInitHelper;

/**
 * RecentSpreadResult test
 *
 * @author Stéphane Bouclier
 */
public class RecentSpreadResultTest {

    @Test
    public void should_not_access_private_constructor() throws Throwable {
        final Constructor<RecentSpreadResult.Spread> constructor = RecentSpreadResult.Spread.class.getDeclaredConstructor();

        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void should_return_to_string() throws IOException {

        // Given
        RecentSpreadResult mockResult = MockInitHelper.buildRecentSpreadResult();

        // When
        final String toString = mockResult.toString();

        // Then
        assertThat(toString, StringContains.containsString("result={XXBTZEUR=[Spread"));
    }
}
