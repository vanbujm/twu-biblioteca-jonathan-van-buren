package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestBibliotecaApp {

    @Test
    public void testWelcomeMessageSaysWelcome() {
        assertEquals("Welcome to Biblioteca!", BibliotecaApp.welcome());
    }

    @Test
    public void BibliotecaAcceptsALibrary() {
        ArrayList<LibraryBook> mockLibrary = new ArrayList<LibraryBook>();
        BibliotecaApp testLibrary = new BibliotecaApp(mockLibrary);
        assertEquals(mockLibrary, testLibrary.getLibrary());
    }

    @Test
    public void testListAllLibraryBooks() {
        String expectedOutput = "----- Library Books -----" +
                                "In Search of Lost Time by Marcel Proust\n" +
                                "Ulysses by James Joyce\n" +
                                "Don Quixote by Miguel de Cervantes";
        assertEquals(expectedOutput, BibliotecaApp.listAllLibraryBooks());
    }

}
