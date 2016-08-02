package com.twu.biblioteca;

import com.twu.loginService.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBibliotecaApp {

    private static final String MAIN_MENU =  "Menu:\n" +
                                             "1) List Books\n" +
                                             "2) Checkout Book\n" +
                                             "3) Return Book\n" +
                                             "4) List Movies\n" +
                                             "5) Checkout Movie\n" +
                                             "6) Return Movie\n" +
                                             "7) Exit\n" +
                                             "\n" +
                                             "Please select a number and press enter\n";
    private static final String WELCOME_MENU = "Welcome to Biblioteca!\n" +
                                                MAIN_MENU;
    private static final String LIST_BOOKS = "1\n";
    private static final String CHECKOUT_BOOK = "2\n";
    private static final String RETURN_BOOK = "3\n";
    private static final String LIST_MOVIES = "4\n";
    private static final String CHECKOUT_MOVIE = "5\n";
    private static final String RETURN_MOVIE = "6\n";
    private static final String EXIT = "7\n";

    private HashMap<String, List<LibraryMedia>> mockLibrary;
    private BibliotecaApp app;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream in;
    private LoginService loginService;

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
        in = new ByteArrayInputStream(EXIT.getBytes());
        mockLibrary = new HashMap<String, List<LibraryMedia>>();
        ArrayList<LibraryMedia> books = new ArrayList<LibraryMedia>();
        books.add(new LibraryBook("Moby Dick", "Herman Melville", 1851));
        mockLibrary.put("book", books);
        ArrayList<LibraryMedia> movies = new ArrayList<LibraryMedia>();
        movies.add(new LibraryMovie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9));
        mockLibrary.put("movie", movies);
        loginService = new LoginService();

        app = new BibliotecaApp(mockLibrary, in, loginService);
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
        assertTrue(app.getBook("Doesn't Exist", "Should Fail").isNullMedia());
    }

    @Test
    public void libraryListsAllLibraryBooks() {
        String expectedOutput = "----- book Library -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n";
        assertEquals(expectedOutput, app.listAllLibraryMedia("book"));
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
        String expectedOutput = "----- book Library -----\n";
        assertEquals(expectedOutput, app.listAllLibraryMedia("book"));
    }

    @Test
    public void returningMobyDickGivesThankyouMessage() {
        app.getBook("Moby Dick", "Herman Melville").checkOut();
        assertEquals("Thank you for returning the book.",app.getBook("Moby Dick", "Herman Melville").returnMedia());
    }

    @Test
    public void youCantReturnUlysses() {
        assertEquals("That is not a valid book to return.", app.getBook("Ulysses", "James Joyce").returnMedia());
    }


    @Test
    public void youCanStartApp() {
        app.run();
        assertEquals(WELCOME_MENU, outContent.toString());
    }

    @Test
    public void menuRequestValidMenuItem() {
        in = new ByteArrayInputStream(("fail\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "Select a valid option!\n";
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void menuCanListBooks() {
        in = new ByteArrayInputStream((LIST_BOOKS + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "----- book Library -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void checkoutBookFromMenu() {
        in = new ByteArrayInputStream((LIST_BOOKS + CHECKOUT_BOOK + "Moby Dick\nHerman Melville\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "----- book Library -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                                MAIN_MENU +
                                "Please type the title of the book you wish to checkout then press enter\n" +
                                "Please type the author of the book you wish to checkout then press enter\n" +
                                "Thank you! Enjoy the book\n" +
                                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void failsToCheckoutBooksFromMenu() {
        in = new ByteArrayInputStream((LIST_BOOKS + CHECKOUT_BOOK + "Not a\nreal book\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "----- book Library -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                                MAIN_MENU +
                                "Please type the title of the book you wish to checkout then press enter\n" +
                                "Please type the author of the book you wish to checkout then press enter\n" +
                                "That book is not available.\n" +
                                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void returnBookFromMenu() {
        in = new ByteArrayInputStream((LIST_BOOKS + CHECKOUT_BOOK + "Moby Dick\nHerman Melville\n" + RETURN_BOOK + "Moby Dick\nHerman Melville\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "----- book Library -----\n" +
                                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                                MAIN_MENU +
                                "Please type the title of the book you wish to checkout then press enter\n" +
                                "Please type the author of the book you wish to checkout then press enter\n" +
                                "Thank you! Enjoy the book\n" +
                                MAIN_MENU +
                                "Please type the title of the book you wish to return then press enter\n" +
                                "Please type the author of the book you wish to return then press enter\n" +
                                "Thank you for returning the book.\n" +
                                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void failsToReturnBook() {
        in = new ByteArrayInputStream((LIST_BOOKS + CHECKOUT_BOOK + "Moby Dick\nHerman Melville\n" + RETURN_BOOK + "This should\nFail\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                "----- book Library -----\n" +
                "Title: Moby Dick, Author: Herman Melville, Publication Date: 1851\n" +
                MAIN_MENU +
                "Please type the title of the book you wish to checkout then press enter\n" +
                "Please type the author of the book you wish to checkout then press enter\n" +
                "Thank you! Enjoy the book\n" +
                MAIN_MENU +
                "Please type the title of the book you wish to return then press enter\n" +
                "Please type the author of the book you wish to return then press enter\n" +
                "That is not a valid book to return.\n" +
                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void libraryListsAllLibraryMovies() {
        String expectedOutput = "----- movie Library -----\n" +
                "Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9\n";
        assertEquals(expectedOutput, app.listAllLibraryMedia("movie"));
    }

    @Test
    public void menuCanListMovies() {
        in = new ByteArrayInputStream((LIST_MOVIES + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                "----- movie Library -----\n" +
                "Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9\n" +
                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void checkoutMovieFromMenu() {
        in = new ByteArrayInputStream((LIST_MOVIES + CHECKOUT_MOVIE + "Pulp Fiction\n1994\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                "----- movie Library -----\n" +
                "Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9\n" +
                MAIN_MENU +
                "Please type the title of the movie you wish to checkout then press enter\n" +
                "Please type the year of the movie you wish to checkout then press enter\n" +
                "Thank you! Enjoy the movie\n" +
                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void failsToCheckoutMoviesFromMenu() {
        in = new ByteArrayInputStream((LIST_MOVIES + CHECKOUT_MOVIE + "Not a\nreal movie\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                "----- movie Library -----\n" +
                "Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9\n" +
                MAIN_MENU +
                "Please type the title of the movie you wish to checkout then press enter\n" +
                "Please type the year of the movie you wish to checkout then press enter\n" +
                "That movie is not available.\n" +
                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void returnMovieFromMenu() {
        in = new ByteArrayInputStream((LIST_MOVIES + CHECKOUT_MOVIE + "Pulp Fiction\n1994\n" + RETURN_MOVIE + "Pulp Fiction\n1994\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "----- movie Library -----\n" +
                                "Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9\n" +
                                MAIN_MENU +
                                "Please type the title of the movie you wish to checkout then press enter\n" +
                                "Please type the year of the movie you wish to checkout then press enter\n" +
                                "Thank you! Enjoy the movie\n" +
                                MAIN_MENU +
                                "Please type the title of the movie you wish to return then press enter\n" +
                                "Please type the year of the movie you wish to return then press enter\n" +
                                "Thank you for returning the movie.\n" +
                                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void failsToReturnMovie() {
        in = new ByteArrayInputStream((LIST_MOVIES + CHECKOUT_MOVIE + "Pulp Fiction\n1994\n" + RETURN_MOVIE + "This should\nfail\n" + EXIT).getBytes());
        app = new BibliotecaApp(mockLibrary, in, loginService);
        String expectedOutput = WELCOME_MENU +
                                "----- movie Library -----\n" +
                                "Title: Pulp Fiction, Director: Quentin Tarantino, Release Year: 1994, Rating: 8.9\n" +
                                MAIN_MENU +
                                "Please type the title of the movie you wish to checkout then press enter\n" +
                                "Please type the year of the movie you wish to checkout then press enter\n" +
                                "Thank you! Enjoy the movie\n" +
                                MAIN_MENU +
                                "Please type the title of the movie you wish to return then press enter\n" +
                                "Please type the year of the movie you wish to return then press enter\n" +
                                "That is not a valid movie to return.\n" +
                                MAIN_MENU;
        app.run();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void userLogsIn() {

    }
}
