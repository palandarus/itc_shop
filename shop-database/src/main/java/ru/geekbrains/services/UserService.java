package ru.geekbrains.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.data.UserData;
import ru.geekbrains.entities.Role;
import ru.geekbrains.entities.User;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.repositories.UserRepository;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserData findById(long id) {
        return userRepository.findById(id).map(UserData::new).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doesn't exists (for edit)"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("userService = " + principal);
        System.out.println("userService name = " + principal.getName());
        return findByUsername(principal.getName());
    }

    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    public User createUser(UserData userData) {
        User user = new User();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setUsername(userData.getUsername());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setEmail(userData.getEmail());
        user.setPhone(userData.getPhone());
        user.setRoles(userData.getRoles());
        return userRepository.save(user);
    }

    public void authenticateUser(User user) {
        List<Role> roles = user.getRoles().stream().distinct().collect(Collectors.toList());
        List<GrantedAuthority> authorities = roles.stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Page<User> findAll(Specification<User> spec, int page, int size) {
        return userRepository.findAll(spec, PageRequest.of(page, size));
    }

    public User saveOrUpdate(UserData user) {
        User currentUser=userRepository.getOne(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAddress(user.getAddress());
        currentUser.setDescription(user.getDescription());
        currentUser.setEmail(user.getEmail());
        currentUser.setRoles(user.getRoles());
        currentUser.setPhone(user.getPhone());
        return userRepository.save(currentUser);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
