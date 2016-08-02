package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBibliotecaApp {

    private static final String WELCOME_MENU =  "Welcome to Biblioteca!\n" +
                                                "1) List Books\n" +
                                                "2) Exit\n" +
                                                "\n" +
                                                "Please select a number and press enter\n";
    private ArrayList<LibraryBook> mockLibrary;
    private BibliotecaApp app;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Before
    public void setUp() {
        in = new ByteArrayInputStream("2".getBytes());
        mockLibrary = new ArrayList<LibraryBook>();
        mockLibrary.add(new LibraryBook("Moby Dick", "Herman Melville", 1851));

        app = new BibliotecaApp(mockLibrary, in);
    }

    @After
    public void tearDown() {
        mockLibrary = null;
        app = null;
        in = null;
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
    public void getBookReturnsNullBookIfBookDoesntExist() {
        assertTrue(app.getBook("Doesn't Exist", "Should Fail").isNullBook());
    }

    @Test
    public void libraryListsAllLibraryBooks() {
        String expectedOutput = "----- Library Books -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                                "Please type the title of the book you wish to checkout\n";
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
    public void checkingOutMobyDickGivesThankyouMessage() {
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
        String expectedOutput = "----- Library Books -----\n" +
                                "Please type the title of the book you wish to checkout\n";
        assertEquals(expectedOutput, app.listAllLibraryBooks());
    }

    @Test
    public void returningMobyDickGivesThankyouMessage() {
        app.getBook("Moby Dick", "Herman Melville").checkOut();
        assertEquals("Thank you for returning the book.",app.getBook("Moby Dick", "Herman Melville").returnBook());
    }

    @Test
    public void youCantReturnUlysses() {
        assertEquals("That is not a valid book to return.", app.getBook("Ulysses", "James Joyce").returnBook());
    }


    @Test
    public void youCanStartApp() {
        app.run();
        assertEquals(WELCOME_MENU, outContent.toString());
    }

    @Test
    public void menuRequestValidMenuItem() {
        in = new ByteArrayInputStream("fail\n2".getBytes());
        app = new BibliotecaApp(mockLibrary, in);
        String expectedOutput = WELCOME_MENU +
                                "Select a valid option!\n";
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void menuCanListBooks() {
        in = new ByteArrayInputStream("1".getBytes());
        app = new BibliotecaApp(mockLibrary, in);
        String expectedOutput = WELCOME_MENU +
                                "----- Library Books -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                                "Please type the title of the book you wish to checkout\n";
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }
}
