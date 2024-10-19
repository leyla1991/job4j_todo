package ru.job4j.service.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Priority;
import ru.job4j.repository.priority.PriorityRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SimplePriorityService implements PriorityService {

    private PriorityRepository priorityRepository;

    @Override
    public Collection<Priority> findAll() {
        return priorityRepository.findALl();
    }
}
