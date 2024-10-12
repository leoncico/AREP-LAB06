package escuelaing.edu.co.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The User class represents a user entity in the system.
 * It is mapped to the "users" table in the database using JPA annotations.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The unique identifier for the user (username).
     * It serves as the primary key for the "users" table.
     */
    @Id
    private String username;

    /**
     * The password of the user.
     * It is stored as a plain string but should be hashed before storage for security purposes.
     */
    private String password;

    // Getters and setters for each field

    /**
     * Gets the username of the user.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
