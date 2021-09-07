package edu.pet.votesystem.service;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return repository.findAll();
    }
}
