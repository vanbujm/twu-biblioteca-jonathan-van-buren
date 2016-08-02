package com.twu.biblioteca;

import com.twu.loginService.LoginService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Created by Jon on 2/08/2016.
 */
public class BibliotecaRunner {

    public static void main(String [ ] args) {
        HashMap<String, List<LibraryMedia>> mockLibrary = new HashMap<String, List<LibraryMedia>>();
        ArrayList<LibraryMedia> books = new ArrayList<LibraryMedia>();
        books.add(new LibraryBook("Moby Dick", "Herman Melville", 1851));
        mockLibrary.put("book", books);
        ArrayList<LibraryMedia> movies = new ArrayList<LibraryMedia>();
        movies.add(new LibraryMovie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9));
        mockLibrary.put("movie", movies);
        LoginService loginService = new LoginService();

        BibliotecaApp app = new BibliotecaApp(mockLibrary, System.in, loginService);
        app.run();
    }
}
