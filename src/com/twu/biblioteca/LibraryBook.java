package com.twu.biblioteca;

/**
 * @author Created by vanbujm on 2/08/2016.
 */
class LibraryBook {

    private String author;
    private int publicationDate;
    private String title;
    private boolean checkedOut;

    LibraryBook(String title, String author, int publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.checkedOut = false;
    }

    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    int getPublicationDate() {
        return publicationDate;
    }

    boolean isCheckedOut() {
        return checkedOut;
    }

    String checkOut() {
        if(!checkedOut && !isNullBook()) {
            checkedOut = true;
            return "Thank you! Enjoy the book";
        }
        else {
            return "That book is not available.";
        }
    }

    String returnBook() {
        if(isNullBook()) {
            return "That is not a valid book to return.";
        }
        checkedOut = false;
        return "Thank you for returning the book.";
    }

    Boolean isNullBook() {
        return title == null && author == null && publicationDate == -1;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + ", Author: " + getAuthor() + ", Publication Date: " + Integer.toString(getPublicationDate());
    }
}
