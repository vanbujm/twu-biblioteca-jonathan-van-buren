package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * @author Created by Jon on 2/08/2016.
 */
public class BibliotecaRunner {

    public static void main(String [ ] args) {
        ArrayList<LibraryBook> mockLibrary = new ArrayList<LibraryBook>();
        mockLibrary.add(new LibraryBook("Moby Dick", "Herman Melville", 1851));
        BibliotecaApp app = new BibliotecaApp(mockLibrary, System.in);
        app.run();
    }
}
