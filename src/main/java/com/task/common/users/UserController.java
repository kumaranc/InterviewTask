package com.task.common.users;

import com.task.Responses.NoSuchElementFoundException;
import com.task.authentication.jwt.config.JwtResponse;
import com.task.authentication.jwt.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO user) {
        Object userr  = userService.createOrUpdateUser(user);
        return ResponseEntity.ok(userr);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable int id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ResponseUser>> getAllUsers(@RequestParam int limit, @RequestParam int offset) {
        List<ResponseUser> responseUserList = userService.getAllUsers(offset, limit);
        return ResponseEntity.ok(responseUserList);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) {
        String token = "";
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = userService
                    .loadUserByUsername(authenticationRequest.getUsername());

            token = jwtTokenUtil.generateToken(userDetails);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());;
        }
//        new JwtResponse(token, "Success", "200")
        return ResponseEntity.status(HttpStatus.OK)
                .body(new JwtResponse(token, "Success", "200"));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            NoSuchElementFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
