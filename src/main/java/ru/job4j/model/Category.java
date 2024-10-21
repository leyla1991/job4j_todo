package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Category {

    @Id
    @EqualsAndHashCode.Include
    private int id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Task> tasks = new ArrayList<>();
}
