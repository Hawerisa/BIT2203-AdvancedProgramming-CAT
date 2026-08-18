package org.example;

import org.springframework.stereotype.Repository;

/**
 * UserRepository.java
 * Repository component handling user data operations.
 *
 * @author Nyevu Chea (Reg: SCT221-0595/2024)
 */
@Repository
public class UserRepository {

    public String findUserById(int id) {
        return "Student User #" + id + " (Nyevu Chea)";
    }
}