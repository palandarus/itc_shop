package ru.geekbrains.controller;

import ru.geekbrains.data.UserData;
import ru.geekbrains.entities.Role;
import ru.geekbrains.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserService;
import ru.geekbrains.utils.RoleFilter;
import ru.geekbrains.utils.UserFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private UserService userService;
    private RoleService roleService;

    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

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
            @Valid @ModelAttribute UserData userData,
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

    @PostMapping("/user/remove/{id}")
    public String removeUser(
            @PathVariable("id") Long id,
        Model model
    ){
        userService.remove(id);
        return "redirect:/users";
    }

    @GetMapping("/roles")
    public String showAllRoles(Model model,
                               @RequestParam(defaultValue = "1", name = "p") Integer page,
                               @RequestParam Map<String, String> params
    ) {
        page = (page < 1) ? 1 : page;
        RoleFilter roleFilter = new RoleFilter(params);
        Page<Role> roles = roleService.findAll(roleFilter.getSpec(), page - 1, 5);
        model.addAttribute("roles", roles);
        model.addAttribute("filterDefinition", roleFilter.getFilterDefinition());

        return "roles";
    }

    @GetMapping("/role/edit/{id}")
    public String showRoleEditForm(@PathVariable Long id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role";
    }

    @GetMapping("/role/add")
    public String addRole(
            Model model
    ) {
        model.addAttribute("role", new Role());
        return "role_create_form";
    }

    @PostMapping("/role/add")
    public String addRole(
            @Valid @ModelAttribute Role role,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "role_add_form";
        }
        roleService.saveOrUpdate(role);
        return "redirect:/roles";
    }




    @PostMapping("/role/edit")
    public String showEditForm(@ModelAttribute Role role) {
        roleService.saveOrUpdate(role);
        return "redirect:/roles";
    }

    @PostMapping("/role/remove/{id}")
    public String removeRole(
            @PathVariable("id") Long id,
            Model model
    ){
        roleService.remove(id);
        return "redirect:/roles";
    }


}
