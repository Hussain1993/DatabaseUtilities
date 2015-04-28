package com.Hussain.pink.triangle.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Hussain on 28/04/2015.
 */
public class PropsGetterTest {

    @Test
    public void testGetInstance() {
        PropsGetter getter = PropsGetter.getInstance();
        assertNotNull(getter);
    }

    @Test
    public void testInit() {
        System.setProperty("properties.file.path", "./src/test/resources/DatabaseUtilities.properties");
        PropsGetter getter = PropsGetter.getInstance();
        getter.init();
    }

    @Test
    public void testGetProperty(){
        System.setProperty("properties.file.path", "./src/test/resources/DatabaseUtilities.properties");
        PropsGetter getter = PropsGetter.getInstance();
        getter.init();
        String expectedValue = "value1";
        String actualValue = getter.getProperty("key1");
        assertEquals(expectedValue,actualValue);
    }

    @Test
    public void testInitOverride() {
        System.setProperty("system.properties.file.path", "./src/test/resources/DatabaseUtilities.properties");
        PropsGetter getter = PropsGetter.getInstance();
        getter.init("system.properties.file.path");
    }
}