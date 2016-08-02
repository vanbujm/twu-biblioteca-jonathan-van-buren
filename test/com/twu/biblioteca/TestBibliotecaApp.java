package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBibliotecaApp {

    private ArrayList<LibraryBook> mockLibrary;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        mockLibrary = new ArrayList<LibraryBook>();
        mockLibrary.add(new LibraryBook("Moby Dick", "Herman Melville", 1851));

        app = new BibliotecaApp(mockLibrary);
    }

    @After
    public void tearDown() throws Exception {
        mockLibrary = null;
        app = null;
    }

    @Test
    public void welcomeMessageSaysWelcome() {
        assertEquals("Welcome to Biblioteca!", app.welcome());
    }

    @Test
    public void bibliotecaAcceptsALibrary() {
        assertEquals(mockLibrary, app.getLibrary());
    }

    @Test
    public void canRetrieveBookFromLibrary() {
        assertEquals("Moby Dick", app.getBook("Moby Dick", "Herman Melville").getTitle());
    }

    @Test
    public void getBookReturnsNullIfBookDoesntExist() {
        assertEquals(null, app.getBook("Doesn't Exist", "Should Fail"));
    }

    @Test
    public void libraryListsAllLibraryBooks() {
        String expectedOutput = "----- Library Books -----\n" +
                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n";
        assertEquals(expectedOutput, app.listAllLibraryBooks());
    }

    @Test
    public void menuContainsListBooks() {
        assertEquals("List Books", app.getMainMenu().get(0));
    }

    @Test
    public void NinetyNineIsAnInvalidMenuOption() {
        assertEquals("Select a valid option!", app.selectItem(99));
    }

    @Test
    public void checkingOutMobyDickGivesMessage() {
        assertEquals("Thank you! Enjoy the book", app.getBook("Moby Dick", "Herman Melville").checkOut());
    }

    @Test
    public void mobyDickIsUnavailable() {
        app.getBook("Moby Dick", "Herman Melville").checkOut();
        assertEquals("That book is not available.", app.getBook("Moby Dick", "Herman Melville").checkOut());
    }

    @Test
    public void mobyDickDoesntShowUpOnList() {
        app.getBook("Moby Dick", "Herman Melville").checkOut();
        assertEquals("----- Library Books -----\n", app.listAllLibraryBooks());
    }
}
