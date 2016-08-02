package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

class BibliotecaApp {

    private List<LibraryBook> library;
    private ArrayList<String> mainMenu;

    BibliotecaApp(List<LibraryBook> library) {
        this.mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
        this.library = library;
    }

    String welcome() {
        return "Welcome to Biblioteca!";
    }

    String listAllLibraryBooks() {
        String output = "----- Library Books -----\n";
        for(LibraryBook book : library) {
            if(!book.isCheckedOut()) {
                output += book + "\n";
            }
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

    List getMainMenu() {
        return mainMenu;
    }

    String selectItem(int optionNumber) {
        int actualIndex = optionNumber - 1;
        try {
            mainMenu.get(actualIndex);
        } catch (IndexOutOfBoundsException e) {
            return "Select a valid option!";
        }
        return Integer.toString(actualIndex);
    }
}
