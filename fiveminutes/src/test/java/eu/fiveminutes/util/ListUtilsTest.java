package eu.fiveminutes.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public final class ListUtilsTest {

    private ListUtils listUtils;

    @Before
    public void setUp() {
        listUtils = new ListUtilsImpl();
    }

    @Test
    public void testNullIsEmpty() {
        Assert.assertTrue(listUtils.isEmpty(null));
    }

    @Test
    public void testIsNewArrayListEmpty() {
        Assert.assertTrue(listUtils.isEmpty(new ArrayList()));
    }

    @Test
    public void testListNotEmpty() {
        final List<String> list = new ArrayList<>();
        list.add("test");

        Assert.assertFalse(listUtils.isEmpty(list));
    }
}
