package edu.pet.votesystem.rest;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.service.UserService;
import edu.pet.votesystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    // http://localhost:8080/votesystem/users
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public List<User> getAll() {
        return service.getAll();
    }


    // http://localhost:8080/votesystem/users/3
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public User get(@PathVariable("id") Integer id) {
        return service.get(id);
    }

    //http://localhost:8080/votesystem/users/add
/*    {
        "name":"Fedor",
            "password": "password",
            "email": "fedor@mail.ru",
            "role": "USER",
            "enable": "true"
    }*/
    @PutMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    public Result add(@RequestBody User user) {
        return service.update(user, null);
    }

    //http://localhost:8080/votesystem/users/edit/7
/*    {
        "name":"Rudik",
            "password": "password",
            "email": "rudolf@mail.ru",
            "role": "ADMIN",
            "enable": "true"
    }*/
    @PutMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write') || hasAuthority('read')")
    public Result edit(@RequestBody User user, @PathVariable("id") Integer id) {
        return service.update(user, id);
    }

    // http://localhost:8080/votesystem/users/delete/3
    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('write') || hasAuthority('read')")
    public Result delete(@PathVariable("id") Integer id) {
        return service.delete(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
