package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/{id}")
    public String getByIdUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user_details";
    }

    @GetMapping(value = "/new")
    public String getCreateUserPage(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.addUser(user);
        return "redirect:admin/";
    }

    @GetMapping(value = "/{id}/edit")
    public String getUpdatePage(@PathVariable("id") Long id, Model model) {
//        List<Role> roles = roleService.getRolesList();
        model.addAttribute("users", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRolesList());
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String updatedUserById(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                  @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(id, user);
        return "redirect:";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:";
    }
}
