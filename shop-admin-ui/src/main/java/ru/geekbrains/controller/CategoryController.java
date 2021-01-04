package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controller.repr.CategoryRepr;
import ru.geekbrains.entities.Brand;
import ru.geekbrains.entities.Category;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.services.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String adminCategoriesPage(Model model) {
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/create")
    public String adminCategoryCreatePage(
            Model model
    ) {
        model.addAttribute("category", new CategoryRepr());
        return "category_form";
    }

    @GetMapping("/category/{id}/edit")
    public String adminCategoryEditPage(@PathVariable Long id, Model model) {
        CategoryRepr category = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("category", category);
        return "category_form";
    }

    @PostMapping("/category")
    public String adminUpsertCategory(Model model, RedirectAttributes redirectAttributes, CategoryRepr categoryRepr) {
        model.addAttribute("activePage", "Categories");

        try {
            categoryService.saveOrUpdate(categoryRepr);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating category", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (categoryRepr.getId() == null) {
                return "redirect:/category/create";
            }
            return "redirect:/category/" + categoryRepr.getId() + "/edit";
        }
        return "redirect:/categories";
    }

    @DeleteMapping("/categories/{id}/remove")
    public String adminDeleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
