package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService.java
 * Service component demonstrating Constructor Dependency Injection.
 *
 * @author Nyevu Chea (Reg: SCT221-0595/2024)
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserDetails(int id) {
        return userRepository.findUserById(id);
    }
}