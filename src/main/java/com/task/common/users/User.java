package com.task.common.users;

import com.task.common.address.Address;
import com.task.common.department.Department;
import com.task.common.role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task_users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String username;
    private String password;
    @OneToOne
    private Role role = new Role();
    @OneToOne
    private Department department = new Department();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addressList = new HashSet<>();

    public Set<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Set<Address> addressList) {
        this.addressList = addressList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                '}';
    }
}
