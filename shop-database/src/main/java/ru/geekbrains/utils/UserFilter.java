package ru.geekbrains.utils;

import ru.geekbrains.entities.User;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.repositories.specifications.UserSpecifications;

import java.util.Map;

public class UserFilter {

    private Specification<User> spec;
    private String filterDefinition;

    public UserFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterName = params.get("name");
        if (filterName != null && !filterName.isBlank()) {
            spec = spec.and(UserSpecifications.nameLike(filterName));
            filterDefinitionBuilder.append("&name=").append(filterName);
        }

        String filterUsername = params.get("username");
        if (filterUsername != null && !filterUsername.isBlank()) {
            spec = spec.and(UserSpecifications.usernameLike(filterUsername));
            filterDefinitionBuilder.append("&username=").append(filterUsername);
        }

        String filterAddress = params.get("address");
        if (filterAddress != null && !filterAddress.isBlank()) {
            spec = spec.and(UserSpecifications.addressLike(filterAddress));
            filterDefinitionBuilder.append("&address=").append(filterAddress);
        }

        String filterEmail = params.get("email");
        if (filterEmail != null && !filterEmail.isBlank()) {
            spec = spec.and(UserSpecifications.usernameLike(filterEmail));
            filterDefinitionBuilder.append("&email=").append(filterEmail);
        }

        String filterPhone = params.get("phone");
        if (filterPhone != null && !filterPhone.isBlank()) {
            spec = spec.and(UserSpecifications.usernameLike(filterPhone));
            filterDefinitionBuilder.append("&username=").append(filterPhone);
        }


        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<User> getSpec() {
        return spec;
    }

    public void setSpec(Specification<User> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }

}
