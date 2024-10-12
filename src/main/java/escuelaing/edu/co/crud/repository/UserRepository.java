package escuelaing.edu.co.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import escuelaing.edu.co.crud.model.User;

/**
 * The UserRepository interface provides database access methods for the User entity.
 * It extends the JpaRepository interface, which provides standard CRUD operations 
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a User entity by its username.
     * 
     * @param username the username to search for
     * @return the User entity if found, or null if no user exists with the given username
     */
    User findByUsername(String username);
}
