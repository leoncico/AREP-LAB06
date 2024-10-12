package escuelaing.edu.co.crud.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import escuelaing.edu.co.crud.model.User;
import escuelaing.edu.co.crud.repository.UserRepository;

/**
 * The UserService class provides the business logic for managing user authentication and registration.
 * It interacts with the UserRepository for data persistence and performs password hashing using SHA-256.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor that injects the UserRepository dependency.
     * 
     * @param userRepository the repository responsible for managing user persistence
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validates the user's credentials by checking if the username exists and 
     * if the provided password, after being hashed, matches the stored password.
     * 
     * @param username the username to validate
     * @param password the password to validate
     * @return true if the credentials are valid, false otherwise
     */
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            try {
                String hashedPassword = hashPassword(password);
                return hashedPassword.equals(user.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Creates a new user with a hashed password if the username is not already taken.
     * 
     * @param username the username for the new user
     * @param password the password for the new user
     * @return true if the user is successfully created, false if the username already exists
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available
     */
    public boolean createUser(String username, String password) throws NoSuchAlgorithmException {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashPassword(password));
        userRepository.save(newUser);
        return true;
    }

    /**
     * Hashes the user's password using the SHA-256 algorithm and encodes it in Base64.
     * 
     * @param password the raw password to hash
     * @return the hashed password as a Base64 encoded string
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}
