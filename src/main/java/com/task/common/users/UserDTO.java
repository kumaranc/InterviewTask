package com.task.common.users;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private int id;
    private String name="";
    private String username="";
    private String password="";
    private int roleId;
    private int departmentId;

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public UserDTO(String name, String username, String password, int roleId, int departmentId) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.departmentId = departmentId;
    }

    public UserDTO() {}

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", roleId:'" + roleId + '\'' +
                ", departmentId:'" + departmentId + '\'' +
                '}';
    }
}
