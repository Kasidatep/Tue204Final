package sit.int204.tue.tue204final.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sit.int204.tue.tue204final.dtos.CreateTodoDto;
import sit.int204.tue.tue204final.dtos.PageDto;
import sit.int204.tue.tue204final.dtos.ReturnTodoPageDto;
import sit.int204.tue.tue204final.entities.Todo;
import sit.int204.tue.tue204final.exceptions.TodoNotFoundException;
import sit.int204.tue.tue204final.repositories.TodoRepository;
import sit.int204.tue.tue204final.utils.ListMapper;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;


    public List<ReturnTodoPageDto> findAllTodo() {
        return listMapper.mapList(todoRepository.findAll(), ReturnTodoPageDto.class,modelMapper);
    }


    public Todo createTodo(CreateTodoDto createTodoDto) {
        Todo todo = modelMapper.map(createTodoDto, Todo.class);
        return todoRepository.saveAndFlush(todo);
    }

    public Todo findTodoById(Integer id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Error Not found"));
    }

    public PageDto<ReturnTodoPageDto> findAllTodoPage(Integer page, Integer size, String sort) {
        Sort sortObj = Sort.by(sort);
        Pageable pageable = PageRequest.of(page,size,sortObj);
        return listMapper.toPageDTO(todoRepository.findAll(pageable), ReturnTodoPageDto.class, modelMapper);
    }

    public void deleteTodo(Integer id) {
         if(todoRepository.existsById(id)){
             todoRepository.deleteById(id);
         }else{
             throw new TodoNotFoundException("cannot delete");
         }
    }

    @Transactional
    public void deleteAllTodoByOwner(String owner) {
        if(todoRepository.existsTodoByOwner(owner)){
            todoRepository.deleteAllByOwner(owner);
            todoRepository.saveAllAndFlush(todoRepository.findAll());
        }else{
            throw new TodoNotFoundException("cannot delete + " + owner);
        }
    }
}
