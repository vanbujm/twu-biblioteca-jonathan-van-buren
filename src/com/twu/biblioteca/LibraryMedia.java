package com.twu.biblioteca;

/**
 * @author Created by Jon on 2/08/2016.
 */
abstract class LibraryMedia {

    private String title;
    private boolean checkedOut;
    private String type;

    LibraryMedia() {
        checkedOut = false;
        title = null;
        type = "media";
    }

    String checkOut() {
        if(!isCheckedOut() && !isNullMedia()) {
            setCheckedOut(true);
            return "Thank you! Enjoy the " + type;
        }
        else {
            return "That " + type + " is not available.";
        }
    }

    String returnMedia() {
        if(isNullMedia()) {
            return "That is not a valid " + type + " to return.";
        }
        setCheckedOut(false);
        return "Thank you for returning the " + type + ".";
    }

    Boolean isNullMedia() {
        return title == null;
    }

    private void setCheckedOut(Boolean bool) {
        checkedOut = bool;
    }

    Boolean isCheckedOut() {
        return checkedOut;
    }

    String getTitle() {
        return title;
    }

    void setType(String type) {
        this.type = type;
    }

    void setTitle(String title) {
        this.title = title;
    }
}
