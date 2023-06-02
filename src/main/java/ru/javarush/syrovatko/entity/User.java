package ru.javarush.syrovatko.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")

@Getter @Setter
@ToString
public class User {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "first_name")
    String firstName;
}
