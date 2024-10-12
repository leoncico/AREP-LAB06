package escuelaing.edu.co.crud;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import escuelaing.edu.co.crud.model.User;
import escuelaing.edu.co.crud.repository.UserRepository;
import escuelaing.edu.co.crud.service.UserService;

/**
 * UserServiceTest is a test class for the UserService class.
 * It includes tests for user creation and validation functionalities.
 */
@SpringBootTest
@Transactional
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private String testUsername;
    private String testPassword;

    /**
     * Set up test data before each test case.
     */
    @BeforeEach
    public void setUp() {
        testUsername = "testuser";
        testPassword = "testpassword";
    }

    /**
     * Clean up the database after each test case.
     */
    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    /**
     * Test the successful creation of a new user.
     * It verifies that the user is created and can be found in the repository.
     * 
     * @throws Exception if an error occurs during user creation
     */
    @Test
    public void testCreateUser_Success() throws Exception {
        boolean userCreated = userService.createUser(testUsername, testPassword);
        assertTrue(userCreated, "User should be created successfully");

        User user = userRepository.findByUsername(testUsername);
        assertNotNull(user, "User should be found in the repository");
        assertEquals(testUsername, user.getUsername(), "Username should match");
    }

    /**
     * Test the creation of a user with an existing username.
     * It verifies that the second creation attempt fails.
     * 
     * @throws Exception if an error occurs during user creation
     */
    @Test
    public void testCreateUser_UsernameExists() throws Exception {
        userService.createUser(testUsername, testPassword);
        boolean userCreatedAgain = userService.createUser(testUsername, "newpassword");
        
        assertFalse(userCreatedAgain, "User creation should fail if username already exists");
    }

    /**
     * Test the validation of a user with correct credentials.
     * It verifies that the user can be successfully validated when the correct password is provided.
     * 
     * @throws Exception if an error occurs during user creation
     */
    @Test
    public void testValidateUser_Success() throws Exception {
        userService.createUser(testUsername, testPassword);
        boolean isValid = userService.validateUser(testUsername, testPassword);
        
        assertTrue(isValid, "User should be validated successfully with correct credentials");
    }

    /**
     * Test the validation of a user with an incorrect password.
     * It verifies that the user cannot be validated when an incorrect password is provided.
     * 
     * @throws Exception if an error occurs during user creation
     */
    @Test
    public void testValidateUser_InvalidPassword() throws Exception {
        userService.createUser(testUsername, testPassword);
        boolean isValid = userService.validateUser(testUsername, "wrongpassword");
        
        assertFalse(isValid, "User should not be validated with incorrect password");
    }

    /**
     * Test the validation of a non-existent user.
     * It verifies that a user who does not exist in the repository cannot be validated.
     */
    @Test
    public void testValidateUser_NonExistentUser() {
        boolean isValid = userService.validateUser("nonexistentuser", "password");
        
        assertFalse(isValid, "Non-existent user should not be validated");
    }
}
