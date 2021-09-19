package edu.pet.votesystem.service;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.repository.UserRepository;
import edu.pet.votesystem.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        LOGGER.info("Get all users");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User get(Integer id) {
        LOGGER.info("Get user by id = {}", id);
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) {
            LOGGER.error("User with id = {} does not exist", id);
            return null;
        }
        return optionalUser.get();
    }

    @Transactional
    public Result update(User user, Integer id) {
        LOGGER.info("Update user with id = {}", id);
        if (id != null){
            repository.update(id, user.getName(), user.getPassword(), user.getEmail(), user.getRole(), user.isEnable());
            return Result.SUCCESS;
        }
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(e.getMessage());
            return Result.FAIL;
        }
        return Result.SUCCESS;
    }

    @Transactional
    public Result delete(Integer id) {
        LOGGER.info("Delete user by id = {}", id);
        return repository.delete(id) != 0 ? Result.SUCCESS : Result.NO_SUCH_ENTRY_EXIST;
    }
}
