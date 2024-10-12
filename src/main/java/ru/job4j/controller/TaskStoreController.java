package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Task;
import ru.job4j.service.TaskStoreService;

@Controller
@RequestMapping("/tasks")
public class TaskStoreController {

   private final TaskStoreService service;

   public TaskStoreController(TaskStoreService service) {
        this.service = service;
   }

   @GetMapping
   public String allTask(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "tasks/list";
   }


   @GetMapping("/doneTask")
   public String doneTask(Model model) {
       model.addAttribute("tasks", service.findByDone());
       return "tasks/list";
   }

   @GetMapping("/newTask")
   public String newTasks(Model model) {
       model.addAttribute("tasks", service.findByNewTask());
       return "tasks/list";
   }

   @GetMapping("/new")
   public String getNewTask(Model model) {
       model.addAttribute("task", service.findAll());
       return "tasks/new";
   }

   @PostMapping("/new")
    public String newTask(@ModelAttribute Task newTask, Model model) {
       service.save(newTask);
       model.addAttribute("message", "Задание успешно сохранено");
       return "redirect:/tasks";
   }

   @GetMapping("/one/{id}")
   public String findById(@PathVariable int id, Model model) {
       var task = service.findById(id);
       if (task.isEmpty()) {
           model.addAttribute("message", "Данного задания не существует");
           return "errors/404";
       }
       model.addAttribute("task", task.get());
       return "tasks/one";
   }

   @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable int id, Model model) {
       var taskOptional = service.findById(id);
       if (taskOptional.isEmpty()) {
           model.addAttribute("message", "Такого задания не существует");
           return "errors/404";
       }
       var del = service.deleteTask(id);
       if (!del) {
           model.addAttribute("message", "Не удалось удалить задачу");
           return "errors/404";
       }
       return "redirect:/tasks";
   }

   @GetMapping("/{id}/update")
   public String getUpdateTask(@PathVariable int id, Model model) {
       model.addAttribute("task", service.findById(id));
       return "tasks/update";
   }

   @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task, Model model) {
       var isUpdate = service.update(task);
       if (!isUpdate) {
           model.addAttribute("message", "Задача не обновилась, попробуйте еще раз");
           return "errors/404";
       }

       return "redirect:/tasks";
   }

   @GetMapping("/done/{id}")
    public String doneTaskButton(@PathVariable int id, Model model) {
        var rsl = service.changeDone(id);
        if (!rsl) {
            model.addAttribute("message", "Что-то пошло не так");
            return "errors/404";
        }
        return "redirect:/tasks";
   }
}
