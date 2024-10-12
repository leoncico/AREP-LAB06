package escuelaing.edu.co.crud;

import escuelaing.edu.co.crud.model.Property;
import escuelaing.edu.co.crud.repository.PropertyRepository;
import escuelaing.edu.co.crud.service.PropertyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;

    private Property property;

    @BeforeEach
    void setUp() {
        propertyRepository.deleteAll();
        property = new Property("123 Main St", 100000, 120, "Beautiful house");
    }

    @Test
    void testGetAllProperties() {
        propertyRepository.save(property);

        List<Property> properties = propertyService.getAllProperties();

        assertNotNull(properties);
        assertFalse(properties.isEmpty());
        assertEquals(1, properties.size());
    }

    @Test
    void testGetPropertyById() {
        Property savedProperty = propertyRepository.save(property);

        Property result = propertyService.getPropertyById(savedProperty.getId());

        assertNotNull(result);
        assertEquals(savedProperty.getAddress(), result.getAddress());
    }

    @Test
    void testCreateProperty() {
        propertyService.createProperty(property);
        Property savedProperty = propertyService.getPropertyById(property.getId());
        assertNotNull(savedProperty);
        assertEquals(property.getAddress(), savedProperty.getAddress());
        assertEquals(property.getPrice(), savedProperty.getPrice());
    }

    @Test
    void testUpdateProperty() {
        Property savedProperty = propertyRepository.save(property);
        Property updatedProperty = new Property("456 Oak St", 120000, 140, "Updated house");

        propertyService.updateProperty(savedProperty.getId(), updatedProperty);

        Property result = propertyRepository.findById(savedProperty.getId()).orElse(null);

        assertNotNull(result);
        assertEquals("456 Oak St", result.getAddress());
        assertEquals(120000, result.getPrice());
    }

    @Test
    void testDeleteProperty() {
        Property savedProperty = propertyRepository.save(property);

        propertyService.deleteProperty(savedProperty.getId());

        Property result = propertyRepository.findById(savedProperty.getId()).orElse(null);
        assertNull(result);
    }
}
