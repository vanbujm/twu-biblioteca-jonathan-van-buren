package com.twu.biblioteca;

import java.util.List;

class BibliotecaApp {

    private List<LibraryBook> library;

    BibliotecaApp(List<LibraryBook> library) {
        this.library = library;
    }

    String welcome() {
        return "Welcome to Biblioteca!";
    }

    String listAllLibraryBooks() {

        return "----- Library Books -----" +
                "In Search of Lost Time by Marcel Proust\n" +
                "Ulysses by James Joyce\n" +
                "Don Quixote by Miguel de Cervantes";
    }

    List<LibraryBook> getLibrary() {
        return library;
    }

    LibraryBook getBook(String title, String author) {
        for(LibraryBook book : library) {
            if(book.getTitle().equals(title) && book.getAuthor().equals(author))
                return book;
        }
        return null;
    }
}
