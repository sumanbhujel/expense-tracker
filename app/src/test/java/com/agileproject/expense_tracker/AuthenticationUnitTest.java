package com.agileproject.expense_tracker;

import com.agileproject.expense_tracker.bll.AuthBLL;
import com.agileproject.expense_tracker.models.User;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class AuthenticationUnitTest {

    private AuthBLL authBLL;

    @Before
    public void setup(){
        authBLL = new AuthBLL();
    }

    @Test
    public void testA_emptySignUpInput_shouldNotCreateANewUser() {
        User user = new User("","Test","test@gmail.com","password");
        boolean signeUp = authBLL.registerUser(user);
        assertFalse(signeUp);
    }

    @Test
    public void testC_validSignUpInputs_shouldCreateANewUser() {
        User user = new User("Neww", "Test", "unittest@example.com", "password");
        boolean signedUp = authBLL.registerUser(user);
        assertFalse(signedUp);
    }

    @Test
    public void testB_invalidSignUpInputs_shouldNotCreateANewUser() {
        User user = new User("New", "Name", "new@example.com", "new");
        boolean signedUp = authBLL.registerUser(user);
        assertTrue(signedUp);
    }

    @Test
    public void testD_existingEmail_shouldNotCreateANewUser() {
        User user = new User("New", "Test", "unittest@example.com", "password");
        boolean signedUp = authBLL.registerUser(user);
        assertFalse(signedUp);
    }

    // Sign In Test

    @Test
    public void testE_emptySignInInputs_shouldDenyLogin() {

        User authUser = authBLL.loginUser("", "password");

        assertNull(authUser);
    }

    @Test
    public void testF_invalidSignInInputs_shouldDenyLogin() {

        User authUser = authBLL.loginUser("unittest@example.com", "passwords");

        assertNull(authUser);
    }

    @Test
    public void testG_validSignInInputs_shouldReturnAuthUser() {

        User authUser = authBLL.loginUser("unittest@example.com", "password");

        assertEquals("unittest@example.com", authUser.getEmail());
    }

}
