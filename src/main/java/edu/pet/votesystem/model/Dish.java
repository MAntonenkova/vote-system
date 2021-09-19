package edu.pet.votesystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;

@Table(name = "vs_dishes")
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id", nullable = false, unique = true)
    private Integer dishId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;
    @NotBlank (message = "dish name should not be empty")
    @Size(min = 2, max = 100)
    @Column(name = "dish_name", nullable = false)
    private String dishName;
    @NotEmpty
    @Column(name = "dish_price", nullable = false)
    @Positive
    @Min(value = 0, message = "dish price should be greater than zero")
    private Integer price;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
