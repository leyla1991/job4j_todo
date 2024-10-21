package ru.job4j.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String title;
    private String description;
    private LocalDateTime created = LocalDateTime.now().withNano(0);
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "jointly",
    joinColumns = {
            @JoinColumn(name = "task_id")
    },
            inverseJoinColumns = {
            @JoinColumn(name = "categories_id")
            }
    )
    private List<Category> categories = new ArrayList<>();
}
