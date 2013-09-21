package org.joow.cycliclist;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

@Test
public class CyclicListTest {
    @Test(enabled = false)
    public void testSizeOfCyclicList() {
        final int size = 10;
        final CyclicList cyclicList = new CyclicList(size);

        assertEquals(cyclicList.size(), size);
    }

    public void getFirstElementOfCyclicList() {
        final CyclicList<String> cyclicList = new CyclicList<>();
        cyclicList.add("first");

        assertEquals(cyclicList.get(0), "first");
    }

    public void getLastElementOfCyclicList() {
        final CyclicList<String> cyclicList = new CyclicList<>();
        cyclicList.addAll(Arrays.asList("one", "two", "three"));

        assertEquals(cyclicList.get(-1), "three");
    }
}
