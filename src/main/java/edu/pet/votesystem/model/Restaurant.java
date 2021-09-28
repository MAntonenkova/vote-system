package edu.pet.votesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Table(name = "vs_restaurants")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_id", nullable = false, unique = true)
    private Long restaurantId;
    @NotEmpty
    @Size(min = 2, max = 100, message = "restaurant's name should be between 2 and 100 characters")
    @Column(name = "rest_name", nullable = false)
    private String restaurantName;
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY,
            mappedBy = "restaurant")
    private List<Dish> dishes;
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY,
            mappedBy = "restaurant")
    private List<Vote> votes;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
