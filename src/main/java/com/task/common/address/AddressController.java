package com.task.common.address;

import com.task.Responses.ErrorResponse;
import com.task.Responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createAddress(@RequestBody AddressDTO addressDTO
            , @RequestHeader(name="Authorization") String token) {
        boolean filter = addressService.validateWithDepartmentName(token, addressDTO);
        if(!filter) {
            return ResponseEntity.ok(new ErrorResponse("404", "Invalid Department", "Failed"));
        }
        Object address = addressService.createAddress(addressDTO);
        return ResponseEntity.ok(address);
    }


}
