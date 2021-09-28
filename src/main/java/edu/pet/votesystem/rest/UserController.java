package edu.pet.votesystem.rest;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.service.UserService;
import edu.pet.votesystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public User get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PutMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public Result add(@RequestBody User user) {
        return service.update(user, null);
    }

    @PutMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('read')")
    public Result edit(@RequestBody User user, @PathVariable("id") Long id) {
        return service.update(user, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('read')")
    public Result delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
