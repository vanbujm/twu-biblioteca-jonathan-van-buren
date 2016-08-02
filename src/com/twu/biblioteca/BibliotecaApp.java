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
        mainMenu.add("Checkout Book");
        this.library = library;
        this.stream = System.in;
    }

    BibliotecaApp(List<LibraryBook> library, InputStream stream) {
        this.mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
        mainMenu.add("Checkout Book");
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
        String input = userInput.nextLine();
        input = waitForMainMenuInput(userInput, input);
        handleInput(input, userInput);
    }

    private String waitForMainMenuInput(Scanner userInput, String input) {
        while(getValidInput(input) == -1) {
            System.out.print("Select a valid option!\n");
            input = userInput.nextLine();
        }
        return input;
    }

    private void handleInput(String input, Scanner userInput) {
        if(!isExitItem(input)) {
            String selection = mainMenu.get(Integer.parseInt(selectItem(getValidInput(input))));
            if(selection.equals("List Books")) {
                System.out.print(listAllLibraryBooks());
                printMainMenu();
                String newInput = userInput.nextLine();
                newInput = waitForMainMenuInput(userInput, newInput);
                handleInput(newInput, userInput);
            }
            if(selection.equals("Checkout Book")) {
                System.out.print("Please type the title of the book you wish to checkout then press enter\n");
                String title = userInput.nextLine();
                System.out.print("Please type the author of the book you wish to checkout then press enter\n");
                String author = userInput.nextLine();
                System.out.print(getBook(title, author).checkOut() + "\n");
            }
        }
    }

    private boolean isExitItem(String input) {
        return getValidInput(input) == mainMenu.size() + 1;
    }

    private int getValidInput(String input) {
        try {
            int testNumber = Integer.parseInt(input);
            if(0 < testNumber && testNumber <= (mainMenu.size() + 1)) {
                return testNumber;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
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
        System.out.print("\nPlease select a number and press enter\n");
    }
}
