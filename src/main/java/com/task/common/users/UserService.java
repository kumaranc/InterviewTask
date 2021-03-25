package com.task.common.users;

import com.task.Responses.ErrorResponse;
import com.task.Responses.HTTPStatus;
import com.task.Responses.PostResponse;
import com.task.common.address.Address;
import com.task.common.department.Department;
import com.task.common.department.DepartmentService;
import com.task.common.role.Role;
import com.task.common.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private DepartmentService departmentService;
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Object createOrUpdateUser(UserDTO userdto) {
        try {
            User user = mapUserDTOtoUserDAO.apply(userdto);
            user = userRepository.save(user);
            System.out.println(user);
            return new PostResponse(user.getId(), HTTPStatus.SUCCESS.getStatus(),
                    "User Created Successfully", "Success");
        } catch (Exception ex) {
            return new ErrorResponse(HTTPStatus.INTERNAL_ERROR.getStatus(),
                    ex.getMessage(), "Failed");
        }
    }

    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        User uuser = user.get();
        System.out.println(uuser);
        return uuser;
    }


    public List<ResponseUser>  getAllUsers(int offset, int limit) {
        limit = limit > 0 ? limit : 10;
        Pageable pageable = PageRequest.of(offset, limit);

        Page<User> userList = userRepository.findAll(pageable);
        List<ResponseUser> usersList = mapUserToResponseUser.apply(userList);
        return usersList;
    }

    Function<UserDTO, User> mapUserDTOtoUserDAO = (userDTO) -> {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        System.out.println("Department Idddd >>> " + userDTO.getDepartmentId());
        Department department = departmentService.getDepartmentById(userDTO.getDepartmentId());
        System.out.println(department);
        user.setDepartment(department);

        System.out.println("Role Idddd >>> " + userDTO.getRoleId());
        Role role = roleService.getRoleById(userDTO.getRoleId());
        System.out.println(role);
        user.setRole(role);

        return user;
    };


    Function<Page<User>, List<ResponseUser>> mapUserToResponseUser = (users) -> {
        List<ResponseUser> responseUserList = new ArrayList<>();
        users.stream().forEach(user -> {
            Address address = new Address();
            try {
                address = user.getAddressList().stream().findAny().get();
            } catch (Exception ex){
                System.err.println("No address found");
            }

            ResponseUser responseUser = new ResponseUser(
                user.getId(), user.getName(), user.getRole().getRolename(), user.getDepartment().getDeptname(),
                    address.getCity(), address.getState(), address.getCountry()
            );
            responseUserList.add(responseUser);
        });
        return responseUserList;
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        Set<GrantedAuthority> permission = new HashSet<>();
        permission.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRolename()));
//        permission.add(new SimpleGrantedAuthority(user.getDepartment().getDeptname()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                permission);
    }

    public User findByName(String name) {
        try {
            return userRepository.findByUsername(name);
        } catch (Exception ex) {
            System.out.println("No User found");
            return new User();
        }
    }
}
