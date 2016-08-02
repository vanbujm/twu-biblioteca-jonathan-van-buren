package com.twu.biblioteca;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BibliotecaApp {

    private List<LibraryBook> library;
    private ArrayList<String> mainMenu;
    private InputStream stream;

    BibliotecaApp(List<LibraryBook> library) {
        this.mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
        this.library = library;
        this.stream = System.in;
    }

    BibliotecaApp(List<LibraryBook> library, InputStream stream) {
        this.mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
        this.library = library;
        this.stream = stream;
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
        return new LibraryBook(null, null, -1);
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

    public void run() {
        System.out.print(welcome() + "\n");
        printMainMenu();

        Scanner userInput = new Scanner(stream);
        String input = userInput.next();
        Boolean validInput = false;
        while(!validInput) {
            if(0 < Integer.parseInt(input) && Integer.parseInt(input) <= (mainMenu.size() + 1)) {
                validInput = true;
            }
            else {
                System.out.print("Select a valid option!");
            }
        }
    }

    private void printMainMenu() {
        int lastItemNumber = 1;
        for(int i=1; i < mainMenu.size() + 1; i++) {
            System.out.print(i + ") " + mainMenu.get(i -1) +"\n");
            lastItemNumber = i;
        }
        lastItemNumber++;
        System.out.print(lastItemNumber + ") Exit\n");
        System.out.print("\nPlease select a number and press enter");
    }
}
