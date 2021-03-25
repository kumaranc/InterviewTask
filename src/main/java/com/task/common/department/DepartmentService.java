package com.task.common.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentById(Integer id) {
        System.out.println("Department Id :::: " + id);
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()) {
            System.out.println("Got Department Object");
            return department.get();
        } else {
            System.out.println("Not Getting Department Object");
            return new Department();
        }
    }
}
