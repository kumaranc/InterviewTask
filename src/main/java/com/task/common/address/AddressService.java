package com.task.common.address;

import com.task.Responses.ErrorResponse;
import com.task.Responses.HTTPStatus;
import com.task.Responses.PostResponse;
import com.task.common.users.User;
import com.task.common.users.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@Transactional
public class AddressService {

    private AddressRepository addressRepository;
    private RestTemplate restTemplate;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Object createAddress(AddressDTO addressdto) {
        System.out.println("User Id in address save >>>> " + addressdto.getIpaddress() );
        try {
            Address address = getAddressFromAddressdto.apply(addressdto);
            address = addressRepository.save(address);
            return new PostResponse(address.getId(), HTTPStatus.SUCCESS.getStatus(),
                    "Address Created Successfully", "Success");
        } catch (Exception ex) {
            return new ErrorResponse(HTTPStatus.INTERNAL_ERROR.getStatus(),
                    "Address Created Successfully", "Failed");
        }
//        return new Address();
    }

    Function<AddressDTO, Address> getAddressFromAddressdto = (addressDTO) -> {
        Address address = new Address();

        String addressInfo = restTemplate.getForEntity(
                "http://api.ipstack.com/"+addressDTO.getIpaddress()+"?access_key=626053333c5203dcd83c391d63485afb",
                String.class).getBody();
        JSONObject addressInput = new JSONObject(addressInfo);

        System.out.println("Address Input >> " + "http://api.ipstack.com/"+addressDTO.getIpaddress()+"?access_key=626053333c5203dcd83c391d63485afb");
        System.out.println(addressInfo);
        if(addressInput != null) {
            System.out.println("User Iddd >>> " + addressDTO.getUserId() +" Ipaddress >>> " + addressDTO.getIpaddress());
            User user = userService.getUser(addressDTO.getUserId());

            address.setCity(addressInput.getString("continent_name"));
            address.setCountry(addressInput.getString("country_name"));
            address.setState(addressInput.getString("region_name"));
            user.getAddressList().add(address);
            address.setUser(user);
//            addressRepository.save(address);
//            userRepository.save(user);
        }
        System.out.println("Address Information >>> " + address);
        return address;
    };

    public boolean validateWithDepartmentName(String token, AddressDTO addressDTO) {
        String token1 = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User loggedInUser = userService.findByName(username);
            User registeredUser = userService.getUser(addressDTO.getUserId());

            if(loggedInUser.getDepartment().getDeptname().equals(registeredUser.getDepartment().getDeptname())) {
                System.out.println("Username :: " + loggedInUser.getName() +" Dept ==> "
                        + loggedInUser.getDepartment().getDeptname());
                return true;
            }
            return false;
        }

        return false;
    }
}
