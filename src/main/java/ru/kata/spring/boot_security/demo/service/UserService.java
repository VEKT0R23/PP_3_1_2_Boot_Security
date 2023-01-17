package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByName(String username);

    void addUser(User user);

    void updateUser(Long id, User user);

    void deleteUserById(Long id);

    Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles);
}
