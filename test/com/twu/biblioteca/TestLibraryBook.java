package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Created by vanbujm on 2/08/2016.
 */
public class TestLibraryBook {
    private LibraryBook mobyDick;
    private LibraryBook nullBook;

    @Before
    public void setUp() throws Exception {
        mobyDick = new LibraryBook("Moby Dick", "Herman Melville", 1851);
        nullBook = new LibraryBook(null, null, -1);
    }

    @After
    public void tearDown() throws Exception {
        mobyDick = null;
        nullBook = null;
    }

    @Test
    public void testBookHasTitle() {
        assertEquals("Moby Dick", mobyDick.getTitle());
    }

    @Test
    public void testDetails() {
        assertEquals("Moby Dick", mobyDick.getTitle());
        assertEquals("Herman Melville", mobyDick.getAuthor());
        assertEquals(1851, mobyDick.getPublicationYear());
    }

    @Test
    public void testReadableToString() {
        assertEquals("Title: Moby Dick, Author: Herman Melville, Publication Date: 1851", mobyDick.toString());
    }

    @Test
    public void mobyDickIsNotCheckedOut() {
        assertFalse(mobyDick.isCheckedOut());
    }

    @Test
    public void canCheckOutBook() {
        mobyDick.checkOut();
        assertTrue(mobyDick.isCheckedOut());
    }

    @Test
    public void cantCheckoutNullBook() {
        assertEquals("That book is not available.",nullBook.checkOut());
    }

    @Test
    public void canReturnBook() {
        mobyDick.checkOut();
        mobyDick.returnMedia();
        assertFalse(mobyDick.isCheckedOut());
    }

    @Test
    public void cantReturnNullBook() {
        assertEquals("That is not a valid book to return.", nullBook.returnMedia());
    }
}
