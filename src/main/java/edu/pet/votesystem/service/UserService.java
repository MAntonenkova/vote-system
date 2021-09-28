package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Role;
import edu.pet.votesystem.model.User;
import edu.pet.votesystem.repository.UserRepository;
import edu.pet.votesystem.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired()
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Transactional(readOnly = true)
    @Cacheable("vs_users")
    public List<User> getAll() {
        LOGGER.info("Get all users");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable("vs_users")
    public User get(Long id) {
        LOGGER.info("Get user by id = {}", id);
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) {
            LOGGER.error("User with id = {} does not exist", id);
            return null;
        }
        return optionalUser.get();
    }

    @Transactional
    @CacheEvict(value = "vs_users", allEntries = true)
    public Result update(User user, Long id) {
        LOGGER.info("Update user with id = {}", id);

        User authUser = ServiceUtil.getAuthUser(repository);
        Role authUserRole = authUser.getRole();

        if (id == null && authUserRole.equals(Role.ADMIN)) {
            try {
                String password = passwordEncoder.encode(user.getPassword());
                user.setPassword(password);
                repository.save(user);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                throw new ValidationException(ex);
            }
            return Result.SUCCESS;
        }

        boolean isUserChangeOwnData = userChangeOwnData(id, authUser);

        Long authUserId = authUser.getId();
        if (!authUserRole.equals(Role.ADMIN) && !isUserChangeOwnData) {
            LOGGER.error("Only admin could change another user's data. User id = {}", authUserId);
            return Result.FAIL;
        }
        repository.update(id, user.getName(), passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getRole(), user.isEnable());
        return Result.SUCCESS;
    }

    @Transactional
    @CacheEvict(value = "vs_users", allEntries = true)
    public Result delete(Long id) {
        LOGGER.info("Delete user by id = {}", id);

        User authUser = ServiceUtil.getAuthUser(repository);
        Role authUserRole = authUser.getRole();

        boolean isUserChangeOwnData = userChangeOwnData(id, authUser);

        Long authUserId = authUser.getId();
        if (!authUserRole.equals(Role.ADMIN) && !isUserChangeOwnData) {
            LOGGER.error("Only admin could change another user's data. User id = {}", authUserId);
            return Result.FAIL;
        }
        return repository.delete(id) != 0 ? Result.SUCCESS : Result.NO_SUCH_ENTRY_EXIST;
    }

    private boolean userChangeOwnData(Long id, User authUser) {
        Long authUserId = authUser.getId();
        return authUserId.equals(id);
    }

}
