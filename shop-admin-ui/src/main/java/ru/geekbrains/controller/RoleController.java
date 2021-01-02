package ru.geekbrains.controller;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Role;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.utils.RoleFilter;

import java.util.List;
import java.util.Map;

public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public String showAllRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

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
            @ModelAttribute Role role,
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
