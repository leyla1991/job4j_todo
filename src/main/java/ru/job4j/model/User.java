package ru.job4j.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String fullName;
    @NonNull
    @EqualsAndHashCode.Include
    private String email;
    @NonNull
    private String password;
}
