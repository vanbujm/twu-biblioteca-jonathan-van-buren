package com.twu.biblioteca;

import java.util.List;

class BibliotecaApp {

    private List<LibraryBook> library;

    BibliotecaApp(List<LibraryBook> library) {
        this.library = library;
    }

    static String welcome() {
        return "Welcome to Biblioteca!";
    }

    static String listAllLibraryBooks() {

        return "----- Library Books -----" +
                "In Search of Lost Time by Marcel Proust\n" +
                "Ulysses by James Joyce\n" +
                "Don Quixote by Miguel de Cervantes";
    }

    public List<LibraryBook> getLibrary() {
        return library;
    }
}
