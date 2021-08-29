package edu.pet.votesystem.model;

import javax.persistence.*;

@Table(name = "vs_restaurants")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "rest_id")
    private int id;
    @Column (name = "rest_name")
    private String name;
}
