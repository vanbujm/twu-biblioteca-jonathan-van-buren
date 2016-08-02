package com.twu.biblioteca;

import com.twu.loginService.LoginService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class BibliotecaApp {

    private final LoginService loginService;
    private HashMap<String, List<LibraryMedia>> library;
    private ArrayList<String> mainMenu;
    private final InputStream stream;
    private Boolean loggedIn = false;

    BibliotecaApp(HashMap<String, List<LibraryMedia>> library, InputStream stream, LoginService loginService) {
        populateMainMenu();
        this.library = library;
        this.stream = stream;
        this.loginService = loginService;
    }

    private void populateMainMenu() {
        this.mainMenu = new ArrayList<String>();
        mainMenu.add("List Books");
        mainMenu.add("Checkout Book");
        mainMenu.add("Return Book");
        mainMenu.add("List Movies");
        mainMenu.add("Checkout Movie");
        mainMenu.add("Return Movie");
        mainMenu.add("Login");
    }

    String welcome() {
        return "Welcome to Biblioteca!";
    }

    LibraryBook getBook(String title, String author) {
        for(LibraryMedia media : library.get("book")) {
            LibraryBook book = (LibraryBook)media;
            if(book.getTitle().equals(title) && book.getAuthor().equals(author))
                return book;
        }
        return new LibraryBook(null, null, -1);
    }

    private LibraryMovie getMovie(String title, String year) {
        try {
            int intYear = Integer.parseInt(year);
            for(LibraryMedia media : library.get("movie")) {
                LibraryMovie movie = (LibraryMovie) media;
                if(movie.getTitle().equals(title) && movie.getYear() == intYear)
                    return movie;
            }
        } catch (NumberFormatException e) {
            return new LibraryMovie(null, null, -1, -1);
        }
        return new LibraryMovie(null, null, -1, -1);
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
                System.out.print(listAllLibraryMedia("book"));
            }
            else if(selection.equals("List Movies")) {
                System.out.print(listAllLibraryMedia("movie"));
            }
            else if(selection.equals("Login")) {
                System.out.print("Please enter your libraryBooks number\n");
                String user = userInput.nextLine();
                System.out.print("Please enter your password\n");
                String password = userInput.nextLine();
                if(loginService.login(user, password)) {
                    System.out.print("You are now logged in!\n");
                    loggedIn = true;
                    changeMenuLoggedIn(input);
                }
                else {
                    System.out.print("Invalid username or password\n");
                }
            }
            else if(selection.equals("Logout")) {
                loggedIn = false;
                System.out.print("You are Logged Out\n");
                mainMenu.remove(Integer.parseInt(selectItem(getValidInput(input))));
                mainMenu.remove(mainMenu.size()-1);
                mainMenu.add("Login");
            }
            else if(loggedIn) {
                if(selection.equals("Checkout Book")) {
                    System.out.print("Please type the title of the book you wish to checkout then press enter\n");
                    String title = userInput.nextLine();
                    System.out.print("Please type the author of the book you wish to checkout then press enter\n");
                    String author = userInput.nextLine();
                    System.out.print(getBook(title, author).checkOut(loginService.getUserID()) + "\n");

                }
                if(selection.equals("Return Book")) {
                    System.out.print("Please type the title of the book you wish to return then press enter\n");
                    String title = userInput.nextLine();
                    System.out.print("Please type the author of the book you wish to return then press enter\n");
                    String author = userInput.nextLine();
                    System.out.print(getBook(title, author).returnMedia() + "\n");
                }
                if(selection.equals("Checkout Movie")) {
                    System.out.print("Please type the title of the movie you wish to checkout then press enter\n");
                    String title = userInput.nextLine();
                    System.out.print("Please type the year of the movie you wish to checkout then press enter\n");
                    String year = userInput.nextLine();
                    System.out.print(getMovie(title, year).checkOut(loginService.getUserID()) + "\n");
                }
                if(selection.equals("Return Movie")) {
                    System.out.print("Please type the title of the movie you wish to return then press enter\n");
                    String title = userInput.nextLine();
                    System.out.print("Please type the year of the movie you wish to return then press enter\n");
                    String year = userInput.nextLine();
                    System.out.print(getMovie(title, year).returnMedia() + "\n");
                }
                if(selection.equals("User Info")) {
                    System.out.print("User: " + loginService.getName() + "\n");
                    System.out.print("Email: " + loginService.getEmail() + "\n");
                    System.out.print("Phone Number: " + loginService.getPhoneNumber() + "\n");
                }
            }
            else {
                System.out.print("Please log in before attempting this action\n");
            }
            goBackToMainMenu(userInput);
        }
    }

    private void changeMenuLoggedIn(String input) {
        mainMenu.remove(Integer.parseInt(selectItem(getValidInput(input))));
        mainMenu.add("User Info");
        mainMenu.add("Logout");
    }

    private void goBackToMainMenu(Scanner userInput) {
        printMainMenu();
        String newInput = userInput.nextLine();
        newInput = waitForMainMenuInput(userInput, newInput);
        handleInput(newInput, userInput);
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
        System.out.print("Menu:\n");
        int lastItemNumber = 1;
        for(int i=1; i < mainMenu.size() + 1; i++) {
            System.out.print(i + ") " + mainMenu.get(i -1) +"\n");
            lastItemNumber = i;
        }
        lastItemNumber++;
        System.out.print(lastItemNumber + ") Exit\n");
        System.out.print("\nPlease select a number and press enter\n");
    }

    String listAllLibraryMedia(String type) {
        String output = "----- " + type + " Library -----\n";
        for(LibraryMedia media : library.get(type)) {
            if(!media.isCheckedOut()) {
                output += media + "\n";
            }
        }
        return output;
    }

    HashMap<String, List<LibraryMedia>> getLibrary() {
        return library;
    }
}
