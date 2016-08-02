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

    @Override
    public String toString() {
        return "Title: " + getTitle() + ", Author: " + getAuthor() + ", Publication Date: " + Integer.toString(getPublicationDate());
    }

    boolean isCheckedOut() {
        return checkedOut;
    }

    String checkOut() {
        if(!checkedOut) {
            checkedOut = true;
            return "Thank you! Enjoy the book";
        }
        else {
            return "That book is not available.";
        }
    }
}
