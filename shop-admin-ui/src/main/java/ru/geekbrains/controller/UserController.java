package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.data.UserData;
import ru.geekbrains.entities.Role;
import ru.geekbrains.entities.User;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserService;
import ru.geekbrains.utils.UserFilter;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService=roleService;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model,
                               @RequestParam(defaultValue = "1", name = "p") Integer page,
                               @RequestParam Map<String, String> params
    ) {
        page = (page < 1) ? 1 : page;
        UserFilter userFilter = new UserFilter(params);
        Page<User> users = userService.findAll(userFilter.getSpec(), page - 1, 5);
        model.addAttribute("users", users);
        model.addAttribute("filterDefinition", userFilter.getFilterDefinition());

        return "users";
    }

    @GetMapping("/user/edit/{id}")
    public String showUserEditForm(@PathVariable Long id, Model model) {
        UserData u = userService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", u);
        model.addAttribute("roles", roles);
        return "user";
    }

    @GetMapping("/users/add")
    public String addUser(
            Model model
    ) {
        model.addAttribute("user", new UserData());
        model.addAttribute("roles", roleService.findAll());
        return "user_create_form";
    }

    @PostMapping("/users/add")
    public String addUser(
            @ModelAttribute UserData userData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "user_create_form";
        }
        userService.createUser(userData);
        return "redirect:/users";
    }


    @PostMapping("/users/edit")
    public String showUserEditForm(@ModelAttribute UserData user) {
        userService.saveOrUpdate(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/remove/{id}")
    public String removeUser(
            @PathVariable("id") Long id,
            Model model
    ){
        userService.remove(id);
        return "redirect:/users";
    }

}
