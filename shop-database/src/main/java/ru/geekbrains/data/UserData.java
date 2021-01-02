package ru.geekbrains.data;

import javax.validation.constraints.NotNull;
import ru.geekbrains.entities.Role;
import ru.geekbrains.entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class UserData {
    private Long id;

    @NotNull
    private String username;

    private boolean enabled;

    @NotNull
    private String password;

    private Date birthday;

    private String address;

    private String description;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String email;

    private String phone;

    private Collection<Role> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserData() {
    }

    public UserData(User user) {
        this.id=user.getId();
        this.username = user.getUsername();
        this.enabled = user.isEnabled();
        this.password = user.getPassword();
        this.birthday = user.getBirthday();
        this.address = user.getAddress();
        this.description = user.getDescription();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                '}';
    }
}
