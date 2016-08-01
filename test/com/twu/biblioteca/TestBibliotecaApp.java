package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TestBibliotecaApp {

    private ArrayList<LibraryBook> mockLibrary;
    private BibliotecaApp app;

    @Before
    public void setUp() throws Exception {
        mockLibrary = new ArrayList<LibraryBook>();
        mockLibrary.add(new LibraryBook("Moby Dick", "Herman Melville", 1851));

        app =  new BibliotecaApp(mockLibrary);
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
    
}
