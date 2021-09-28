package edu.pet.votesystem.service;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new UsernameNotFoundException("Authentication failed. User with email: " +  email + " doesn't exist");
        }
        return new MyUserPrincipal(user);
    }
}
