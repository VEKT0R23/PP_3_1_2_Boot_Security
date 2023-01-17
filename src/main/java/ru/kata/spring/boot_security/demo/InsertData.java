package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class InsertData {

    private final UserService userService;

    @Autowired
    public InsertData(UserService userService) {
        this.userService = userService;
            }

    @PostConstruct
    public void insertData() {

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        User user1 = new User();
        Set<Role> adminSet = new HashSet<>();
        Collections.addAll(adminSet, role1, role2);
        user1.setId(1L);
        user1.setUsername("admin");
        user1.setAge(40);
        user1.setPassword("123");
        user1.setRoles(new String[]{"ROLE_ADMIN"});
        userService.addUser(user1);

        User user2 = new User();
        Set<Role> userSet = new HashSet<>();
        Collections.addAll(userSet, role2);
        user2.setId(2L);
        user2.setUsername("user");
        user2.setAge(44);
        user2.setPassword("123");
        user2.setRoles(new String[]{"ROLE_USER"});
        userService.addUser(user2);
    }
}
