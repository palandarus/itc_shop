package utils;

import entities.Role;
import entities.User;
import org.springframework.data.jpa.domain.Specification;
import repositories.specifications.RoleSpecifications;
import repositories.specifications.UserSpecifications;

import java.util.Map;

public class RoleFilter {

    private Specification<Role> spec;
    private String filterDefinition;

    public RoleFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterName = params.get("name");
        if (filterName != null && !filterName.isBlank()) {
            spec = spec.and(RoleSpecifications.nameLike(filterName));
            filterDefinitionBuilder.append("&name=").append(filterName);
        }




        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Role> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Role> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }

}
