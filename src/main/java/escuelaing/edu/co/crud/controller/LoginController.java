package escuelaing.edu.co.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import escuelaing.edu.co.crud.service.UserService;

/**
 * The LoginController class handles user authentication and registration.
 * It exposes HTTP endpoints for login and user registration, validating 
 * credentials and managing new user creation via the UserService.
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private UserService userService;

    /**
     * Constructor that injects the UserService dependency.
     * 
     * @param userService the service responsible for user management
     */
    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

     /**
     * GET request handler for rendering the login page.
     * 
     * @return the name of the view to render, in this case "index"
     */
    @GetMapping
    public String renderLogin(){
        return "index";
    }

     /**
     * POST request handler for user login.
     * Validates the user credentials using the UserService.
     * 
     * @param username the username provided in the request
     * @param password the password provided in the request
     * @return ResponseEntity with status ACCEPTED if valid, BAD_REQUEST otherwise
     */
    @PostMapping
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        if(userService.validateUser(username, password)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * POST request handler for user registration.
     * Creates a new user account if the username is not already taken.
     * 
     * @param username the username to register
     * @param password the password for the new account
     * @return ResponseEntity with status CREATED if successful, 
     *         or BAD_REQUEST if the user already exists.
     *         Returns INTERNAL_SERVER_ERROR in case of an exception.
     */
    @PostMapping("/new")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
        try {
            boolean userCreated = userService.createUser(username, password);
            if (userCreated) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
