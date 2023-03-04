package me.xlucash.todoboard.service;

import me.xlucash.todoboard.model.Task;
import me.xlucash.todoboard.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = false)
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(readOnly = false)
    @Deprecated
    public Task editTask(Long id, Task editedTask) {
        Task task = taskRepository.getById(id);
        task.setTitle(editedTask.getTitle());
        task.setDescription(editedTask.getDescription());
        task.setDoneByDate(editedTask.getDoneByDate());

        return taskRepository.save(task);
    }

    public Iterable<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTask(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
