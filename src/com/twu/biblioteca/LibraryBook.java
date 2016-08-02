package com.twu.biblioteca;

/**
 * @author Created by vanbujm on 2/08/2016.
 */
class LibraryBook extends LibraryMedia {

    private String author;
    private int publicationYear;

    LibraryBook(String title, String author, int publicationYear) {
        setTitle(title);
        this.author = author;
        this.publicationYear = publicationYear;
        setType("book");
    }

    String getAuthor() {
        return author;
    }

    int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + ", Author: " + getAuthor() + ", Publication Date: " + Integer.toString(getPublicationYear());
    }
}
