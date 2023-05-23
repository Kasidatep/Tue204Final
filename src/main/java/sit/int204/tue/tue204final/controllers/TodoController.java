package sit.int204.tue.tue204final.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sit.int204.tue.tue204final.dtos.CreateTodoDto;
import sit.int204.tue.tue204final.dtos.PageDto;
import sit.int204.tue.tue204final.dtos.ReturnTodoPageDto;
import sit.int204.tue.tue204final.entities.Todo;
import sit.int204.tue.tue204final.exceptions.ErrorResponse;
import sit.int204.tue.tue204final.exceptions.TodoNotFoundException;
import sit.int204.tue.tue204final.services.TodoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Todo createTodo(@Valid @RequestBody CreateTodoDto createTodoDto ) throws MethodArgumentNotValidException{
        return todoService.createTodo(createTodoDto);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable Integer id){
         todoService.deleteTodo(id);
    }

    @DeleteMapping("/owner/{owner}")
    public void deleteAllTodoByOwner(@PathVariable String owner){
        todoService.deleteAllTodoByOwner(owner);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Cannot Add your request have error : "+ex.getFieldErrorCount(), webRequest.getDescription(false).substring(4));

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.addValidationError(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}