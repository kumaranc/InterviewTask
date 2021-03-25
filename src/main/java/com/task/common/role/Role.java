package com.task.common.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private int id;
    private String rolename;
    @JsonIgnore
    @CreationTimestamp
    private Timestamp created_at;
    @JsonIgnore
    @UpdateTimestamp
    private Timestamp updated_at;

    public Role() {
    }

    public Role(int id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", rolename:'" + rolename + '\'' +
                '}';
    }
}
