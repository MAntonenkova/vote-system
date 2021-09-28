package edu.pet.votesystem.service;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class ServiceUtil {

    public static String getAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static User getAuthUser(UserRepository repository) {
        String authenticationUserEmail = ServiceUtil.getAuthenticationUser();
        return repository.findByEmail(authenticationUserEmail);
    }

}
