package net.javaguides.springboot.springsecurity.web.dto;

import javax.validation.constraints.NotEmpty;

public class ArticleRegistrationDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String placeOfTheObject;
    @NotEmpty
    private String description;
    @NotEmpty
    private String price;
    @NotEmpty
    private String available;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfTheObject() {
        return placeOfTheObject;
    }

    public void setPlaceOfTheObject(String placeOfTheObject) {
        this.placeOfTheObject = placeOfTheObject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
