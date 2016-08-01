package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
    public void libraryListsAllLibraryBooks() {
        String expectedOutput = "----- Library Books -----" +
                                "In Search of Lost Time by Marcel Proust\n" +
                                "Ulysses by James Joyce\n" +
                                "Don Quixote by Miguel de Cervantes";
        assertEquals(expectedOutput, app.listAllLibraryBooks());
    }

}
