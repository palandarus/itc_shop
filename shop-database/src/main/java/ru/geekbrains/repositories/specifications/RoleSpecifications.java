package ru.geekbrains.repositories.specifications;

import ru.geekbrains.entities.Role;
import org.springframework.data.jpa.domain.Specification;

public class RoleSpecifications {

    public static Specification<Role> nameLike(String namePart) {
        return (Specification<Role>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart)); // where p.title like %titlePart%
    }

}
