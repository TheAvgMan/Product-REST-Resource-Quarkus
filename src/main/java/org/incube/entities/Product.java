package org.incube.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name should not be blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "description should not be blank")
    @Column(name = "description")
    private String description;

    @Min(message = "minimum price is 1.00 egp", value = 1)
    @NotBlank(message = "minimum price is 1.00 egp")
    @Column(name = "price")
    private Double price;

    @NotBlank(message = "image link should not be blank")
    @Column(name = "image")
    private String image;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}