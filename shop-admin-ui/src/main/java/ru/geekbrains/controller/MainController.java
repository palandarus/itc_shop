package ru.geekbrains.controller;

import entities.Role;
import entities.User;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.RoleService;
import services.UserService;
import utils.UserFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private UserService userService;
    private RoleService roleService;

    @RequestMapping("/")
    public String indexPage(Model model){
        model.addAttribute("activePage", "None");
        return "index";
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

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User u = userService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("user", u);
        model.addAttribute("roles", roles);
        return "user";
    }

    @GetMapping("/add")
    public String addUser(
            Model model
    ) {
        model.addAttribute("user", new User());
        return "user_add_form";
    }

    @PostMapping("/add")
    public String addUser(
            @Valid @ModelAttribute User user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "user_add_form";
        }
        userService.saveOrUpdate(user);
        return "redirect:/users";
    }




    @PostMapping("/users/edit")
    public String showEditForm(@ModelAttribute User user) {
        userService.saveOrUpdate(user);
        return "redirect:/users";
    }

    @GetMapping("/users/remove/{id}")
    public String removeUser(
            @PathVariable("id") Long id,
        Model model
    ){
        userService.remove(id);
        return "redirect:/users";
    }

}
