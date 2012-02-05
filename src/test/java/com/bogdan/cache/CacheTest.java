package com.bogdan.cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Bogdan
 */
public class CacheTest {

    private Cache<String, String> cache;

    @Before
    public void setUp() {
        cache = new Cache<String, String>(1);
    }

    @Test
    public void putAndGetTest() {
        cache.put("test", "value");
        String value = cache.get("test");
        assertNotNull("Should get not null value back.", value);
        assertEquals("value", value);
    }

    @Test
    public void leastRecentlyUsedTest() {
        cache = new Cache<String, String>(2);
        assertEquals(0, cache.keySet().size());
        cache.put("key1", "value1");
        assertEquals(1, cache.keySet().size());
        cache.put("key2", "value2");
        assertEquals(2, cache.keySet().size());
        cache.get("key1");
        cache.get("key2");
        cache.put("key3", "value3");
        assertEquals(2, cache.keySet().size());
        assertNull(cache.get("key1"));
        assertNotNull(cache.get("key2"));
        assertNotNull(cache.get("key3"));
    }

}
