package com.twu.loginService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Created by vanbujm on 2/08/2016.
 */
public class TestLoginService {

    private LoginService test;

    @Before
    public void setUp() throws Exception {
        test = new LoginService();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void shouldLogin() {
        assertTrue(test.login("000-0000", "password"));
    }

    @Test
    public void shouldFailToLogin() {
        assertFalse(test.login("not correct", "password"));
    }

    @Test
    public void shouldGetUserInfo() {
        test.login("000-0000", "password");
        assertEquals("Jane", test.getName());
        assertEquals("jane@email.com", test.getEmail());
        assertEquals("0000 000 000", test.getPhoneNumber());
    }

    @Test
    public void repliesWithUnauthorized() {
        assertEquals("Unauthorized", test.getName());
        assertEquals("Unauthorized", test.getEmail());
        assertEquals("Unauthorized", test.getPhoneNumber());
    }
}