package com.twu.biblioteca;

/**
 * Created by vanbujm on 2/08/2016.
 */
class LibraryBook {

    private String author;
    private int publicationDate;
    private String title;

    LibraryBook(String title, String author, int publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
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
}
