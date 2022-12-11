package com.github.sbouclier.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import java.io.IOException;

import org.hamcrest.core.StringContains;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sbouclier.utils.StreamUtils;

public class AddOrderResultTest {

    @Test
    public void should_return_to_string() throws IOException {
        // Given
        String jsonResult = StreamUtils.getResourceAsString(this.getClass(), "json/add_order.mock.json");
        AddOrderResult mockResult = new ObjectMapper().readValue(jsonResult, AddOrderResult.class);
        // When
        String toString = mockResult.toString();
        // Then
        assertThat(toString, containsString("result=AddOrder"));
    }
}
