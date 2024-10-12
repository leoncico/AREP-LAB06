package escuelaing.edu.co.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import escuelaing.edu.co.crud.model.Property;
import escuelaing.edu.co.crud.repository.PropertyRepository;

/**
 * The PropertyService class provides the business logic for managing properties.
 * It acts as an intermediary between the controller layer and the repository layer,
 * handling CRUD operations on Property entities.
 */
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    /**
     * Constructor that injects the PropertyRepository dependency.
     * 
     * @param propertyRepository the repository responsible for managing property persistence
     */
    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     * Retrieves all properties from the database.
     * 
     * @return a list of all Property entities
     */
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Retrieves a specific property by its ID.
     * 
     * @param id the ID of the property to retrieve
     * @return the Property entity if found, or null if not found
     */
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    /**
     * Creates and saves a new property in the database.
     * 
     * @param property the Property entity to be created
     */
    public void createProperty(Property property) {
        propertyRepository.save(property);
    }

    /**
     * Updates an existing property in the database.
     * 
     * @param id the ID of the property to update
     * @param propertyDetails the updated property details
     */
    public void updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id).orElseThrow();
        property.setAddress(propertyDetails.getAddress());
        property.setPrice(propertyDetails.getPrice());
        property.setSize(propertyDetails.getSize());
        property.setDescription(propertyDetails.getDescription());
        propertyRepository.save(property);
    }

    /**
     * Deletes a property from the database by its ID.
     * 
     * @param id the ID of the property to delete
     */
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
