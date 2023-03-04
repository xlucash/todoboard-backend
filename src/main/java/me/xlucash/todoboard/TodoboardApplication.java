package me.xlucash.todoboard;

import me.xlucash.todoboard.model.Task;
import me.xlucash.todoboard.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TodoboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoboardApplication.class, args);
	}

}
