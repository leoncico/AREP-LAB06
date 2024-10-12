package escuelaing.edu.co.crud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import escuelaing.edu.co.crud.model.Property;

/**
 * The PropertyRepository interface provides CRUD operations for the Property entity.
 * It extends the CrudRepository interface, inheriting basic CRUD methods
 */
@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {

    /**
     * Retrieves all Property entities from the database.
     * 
     * @return a list of all Property entities
     */
    List<Property> findAll();
}
