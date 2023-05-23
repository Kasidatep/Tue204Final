package sit.int204.tue.tue204final.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sit.int204.tue.tue204final.dtos.CreateTodoDto;
import sit.int204.tue.tue204final.dtos.PageDto;
import sit.int204.tue.tue204final.dtos.ReturnTodoPageDto;
import sit.int204.tue.tue204final.entities.Todo;
import sit.int204.tue.tue204final.exceptions.ErrorResponse;
import sit.int204.tue.tue204final.exceptions.TodoNotFoundException;
import sit.int204.tue.tue204final.services.TodoService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("")
    public List<ReturnTodoPageDto> findAllTodo(){
        return todoService.findAllTodo();
    }

    @GetMapping("/page")
    public PageDto<ReturnTodoPageDto> findAllTodoPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "id") String sort
    ){
        return todoService.findAllTodoPage(page, size, sort);
    }

    @GetMapping("/{id}")
    public Todo findTodoById(@PathVariable Integer id){
        return todoService.findTodoById(id);
    }

    @PostMapping("")
    public Todo createTodo(@RequestBody CreateTodoDto createTodoDto){
        return todoService.createTodo(createTodoDto);
    }

}
