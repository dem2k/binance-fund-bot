package com.github.sbouclier.utils;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Base64 utility test
 *
 * @author Stéphane Bouclier
 */
public class Base64UtilsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void utilityClassTest() throws Throwable {
        final Constructor<Base64Utils> constructor = Base64Utils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);

        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Test
    public void should_encode_and_decode_data() {
        String originalInput = "my data 123 to encode";

        String encode = Base64Utils.base64Encode(originalInput.getBytes());
        byte[] decode = Base64Utils.base64Decode(encode);

        assertEquals(originalInput, new String(decode));
    }
}
