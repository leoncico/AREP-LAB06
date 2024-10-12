package escuelaing.edu.co.crud.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import escuelaing.edu.co.crud.model.Property;
import escuelaing.edu.co.crud.service.PropertyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The RealStateController class manages CRUD operations for Property entities.
 * It provides endpoints for creating, reading, updating, and deleting properties
 * via the PropertyService.
 */
@RestController
@RequestMapping("/properties")
@CrossOrigin(origins = "*")
public class RealStateController {

    private final PropertyService propertyService;

    /**
     * Constructor that injects the PropertyService dependency.
     * 
     * @param propertyService the service responsible for property management
     */
    @Autowired
    public RealStateController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * GET request handler to retrieve all properties.
     * 
     * @return ResponseEntity containing the list of properties and HTTP status OK
     */
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    /**
     * POST request handler to create a new property.
     * 
     * @param property the property object to be created
     * @return ResponseEntity with HTTP status CREATED upon successful creation
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> createProperty(@RequestBody Property property) {
        propertyService.createProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * GET request handler to retrieve a specific property by its ID.
     * 
     * @param id the ID of the property to retrieve
     * @return ResponseEntity containing the property and HTTP status OK if found, 
     *         or NOT_FOUND if the property does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            return new ResponseEntity<>(property, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * PUT request handler to update an existing property.
     * 
     * @param id the ID of the property to update
     * @param propertyDetails the updated property details
     * @return ResponseEntity with HTTP status OK after successful update
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        propertyService.updateProperty(id, propertyDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * DELETE request handler to remove a property by its ID.
     * 
     * @param id the ID of the property to delete
     * @return ResponseEntity with HTTP status NO_CONTENT after successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
