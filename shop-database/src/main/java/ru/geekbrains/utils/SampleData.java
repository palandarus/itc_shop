package ru.geekbrains.utils;

import ru.geekbrains.entities.Role;
import ru.geekbrains.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.geekbrains.repositories.RoleRepository;
import ru.geekbrains.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

//@Component
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
      /*  User admin=new User("admin","admin@admin.ad","111111",new Date(1987,04,16),"address","description");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setName("Admin");



        Role adminRole=new Role("ROLE_ADMIN");

        admin.getRoles().add(adminRole);

        roleRepository.save(adminRole);
        userRepository.save(admin);*/
    }
}
