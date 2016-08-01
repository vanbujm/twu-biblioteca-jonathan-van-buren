package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by vanbujm on 2/08/2016.
 */
public class TestLibraryBook {
    private LibraryBook mobyDick;

    @Before
    public void setUp() throws Exception {
        mobyDick = new LibraryBook("Moby Dick", "Herman Melville", 1851);
    }

    @After
    public void tearDown() throws Exception {
        mobyDick = null;
    }

    @Test
    public void testBookHasTitle() {
        assertEquals("Moby Dick", mobyDick.getTitle());
    }

    @Test
    public void testDetails() {
        assertEquals("Moby Dick", mobyDick.getTitle());
        assertEquals("Herman Melville", mobyDick.getAuthor());
        assertEquals(1851, mobyDick.getPublicationDate());
    }

    @Test
    public void testReadableToString() {
        assertEquals("Title: Moby Dick, Author: Herman Melville, Publication Date: 1851", mobyDick.toString());
    }
}
