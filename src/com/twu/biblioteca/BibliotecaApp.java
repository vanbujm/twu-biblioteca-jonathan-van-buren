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
        String output = "----- Library Books -----\n";

        for(LibraryBook book : library) {
            output += book + "\n";
        }

        return output;
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
