package com.twu.biblioteca;

/**
 * @author Created by Jon on 2/08/2016.
 */
class LibraryMovie extends LibraryMedia{

    private String director;
    private int year;
    private double rating;

    LibraryMovie(String name, String director, int year, double rating) {
        setTitle(name);
        setType("movie");
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    String getDirector() {
        return director;
    }

    int getYear() {
        return year;
    }

    private double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + ", Director: " + getDirector() + ", Release Year: " + Integer.toString(getYear()) + ", Rating: " + getRating();
    }
}
