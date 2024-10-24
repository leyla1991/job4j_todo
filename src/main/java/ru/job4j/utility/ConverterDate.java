package ru.job4j.utility;

import ru.job4j.model.Task;
import ru.job4j.model.User;

import java.time.ZoneId;
import java.util.Collection;

public class ConverterDate {

    public static Collection<Task> convertTime(Collection<Task> tasks, User user) {
        for (Task task : tasks) {
           task.getCreated()
                   .atZone(ZoneId.of("UTC"))
                    .withZoneSameLocal(ZoneId.of(user.getTimezone()))
                    .toLocalDateTime();
        }
        return tasks;
    }
}
