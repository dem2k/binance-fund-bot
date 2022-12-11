package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

public class WithdrawResultTest {

    @Test
    public void should_return_to_string() throws IOException {
        // Given
        final String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/withdraw_reference.mock.json");
        WithdrawResult mockResult = new ObjectMapper().readValue(jsonResult, WithdrawResult.class);
        // When
        final String toString = mockResult.toString();
        // Then
        assertThat(toString, containsString("result=Withdraw"));
    }
}
