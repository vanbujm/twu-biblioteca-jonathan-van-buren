package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestBibliotecaApp {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testWelcomeMessageSaysWelcome() {
        assertEquals("Welcome to Biblioteca!", BibliotecaApp.welcome());
    }

    @Test
    public void testMainSaysWelcome() {
        BibliotecaApp.main(new String[] {});
        assertEquals("Welcome to Biblioteca!", outContent.toString());
    }
}
