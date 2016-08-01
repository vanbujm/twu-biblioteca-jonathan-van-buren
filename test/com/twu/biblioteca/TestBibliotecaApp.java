package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestBibliotecaApp {

    @Test
    public void testWelcomeMessageSaysWelcome() {
        assertEquals("Welcome to Biblioteca!",new BibliotecaApp().welcome());
    }
}
