package ru.job4j.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private LocalDateTime created = LocalDateTime.now().withNano(0);
    @NonNull
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;
}
