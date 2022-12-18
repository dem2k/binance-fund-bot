package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

/**
 * ServerTimeResult test
 *
 * @author Stéphane Bouclier
 */
public class ServerTimeResultTest {

    @Test
    public void should_return_to_string() throws IOException {
        // Given
        String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/server_time.mock.json");
        ServerTimeResult mockResult = new ObjectMapper().readValue(jsonResult, ServerTimeResult.class);
        // When
        String toString = mockResult.toString();
        // Then
        assertThat(toString, containsString("result=ServerTime"));
    }
}
