package me.xlucash.todoboard.controller;

import me.xlucash.todoboard.model.Task;
import me.xlucash.todoboard.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.findTaskById(id));

        if(!optionalTask.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Task task = optionalTask.get();
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task returnTask = taskService.createTask(task);
            return new ResponseEntity<Task>(returnTask, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/tasks/{id}")
    @Deprecated
    public ResponseEntity<Task> editTask(@PathVariable("id") Long id,@RequestBody Task task) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.findTaskById(id));

        if(!optionalTask.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Task updatedTask = taskService.editTask(id, task);
        return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
    }


    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Long id) {
        try {
            taskService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
