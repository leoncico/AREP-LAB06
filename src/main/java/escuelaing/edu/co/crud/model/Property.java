package escuelaing.edu.co.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Property class represents a real estate property entity.
 * It is mapped to the "properties" table in the database using JPA annotations.
 */
@Entity
@Table(name = "properties")
public class Property {

    /**
     * The unique identifier for each property.
     * It is automatically generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The address of the property.
     * This field is mandatory (cannot be null).
     */
    @Column(nullable = false)
    private String address;

    /**
     * The price of the property.
     * This field is mandatory (cannot be null).
     */
    @Column(nullable = false)
    private double price;

    /**
     * The size of the property in square meters.
     * This field is mandatory (cannot be null).
     */
    @Column(nullable = false)
    private double size;

    /**
     * A brief description of the property.
     * This field is mandatory (cannot be null).
     */
    @Column(nullable = false)
    private String description;

    /**
     * Default no-argument constructor.
     * Required by JPA.
     */
    public Property() {}

    /**
     * Parameterized constructor for initializing a new Property instance with specific details.
     * 
     * @param address the address of the property
     * @param price the price of the property
     * @param size the size of the property in square meters
     * @param description a brief description of the property
     */
    public Property(String address, double price, double size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    // Getters and setters for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
