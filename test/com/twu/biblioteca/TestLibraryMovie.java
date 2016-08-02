package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Created by Jon on 2/08/2016.
 */
public class TestLibraryMovie {

    private LibraryMovie pulpFiction;
    private LibraryMovie nullMovie;

    @Before
    public void setUp() throws Exception {
        pulpFiction = new LibraryMovie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9);
        nullMovie = new LibraryMovie(null, null, -1, -1);
    }

    @After
    public void tearDown() throws Exception {
        pulpFiction = null;
        nullMovie = null;
    }

    @Test
    public void testMovieHasTitle() {
        assertEquals("Pulp Fiction", pulpFiction.getTitle());
    }

    @Test
    public void testDetails() {
        assertEquals("Pulp Fiction", pulpFiction.getTitle());
        assertEquals("Quentin Tarantino", pulpFiction.getDirector());
        assertEquals(1994, pulpFiction.getYear());
    }

    @Test
    public void testReadableToString() {
        assertEquals("Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9", pulpFiction.toString());
    }

    @Test
    public void pulpFictionIsNotCheckedOut() {
        assertFalse(pulpFiction.isCheckedOut());
    }

    @Test
    public void canCheckOutBook() {
        pulpFiction.checkOut("000-0000");
        assertTrue(pulpFiction.isCheckedOut());
    }

    @Test
    public void cantCheckoutnullMovie() {
        assertEquals("That movie is not available.",nullMovie.checkOut("000-0000"));
    }

    @Test
    public void canReturnBook() {
        pulpFiction.checkOut("000-0000");
        pulpFiction.returnMedia();
        assertFalse(pulpFiction.isCheckedOut());
    }

    @Test
    public void cantReturnNullMovie() {
        assertEquals("That is not a valid movie to return.", nullMovie.returnMedia());
    }
}
