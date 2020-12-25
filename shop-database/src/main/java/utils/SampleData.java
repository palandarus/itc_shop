package utils;

import entities.Role;
import entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import repositories.RoleRepository;
import repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class SampleData {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public SampleData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostConstruct
    public void init(){
        User admin=new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setName("Admin");



        Role adminRole=new Role("ROLE_ADMIN");

        admin.getRoles().add(adminRole);

        roleRepository.save(adminRole);
        userRepository.save(admin);
    }
}
