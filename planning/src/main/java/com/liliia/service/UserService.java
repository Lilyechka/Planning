package com.liliia.service;

import com.liliia.model.User;
import com.liliia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public User registerNewUserAccount(UserRegistrationDTO userDto) {
        if (usernameExists(userDto.getName())) {
            throw new RuntimeException("There is an account with that username: " + userDto.getName());
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public UserDTO getCurrentUser(){
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByName(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToDTO(currentUser);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByName(username).isPresent();
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getRole());
    }

    private boolean usernameExists(String username) {
        return userRepository.findByName(username).isPresent();
    }

}
