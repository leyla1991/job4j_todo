package ru.job4j.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;


    private String fullName;
    @EqualsAndHashCode.Include
    private String email;
    private String password;
}
