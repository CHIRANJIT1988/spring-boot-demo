package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/account")
public class AccountController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;


    @PutMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> change(@RequestBody Map<String, String> body, BindingResult bindingResults, Principal principal)
    {
        if(!bindingResults.hasErrors())
        {
            ResponseEntity<?> responseEntity = ValidateModel.isValidPasswordFields(body, bindingResults);

            if(responseEntity.getStatusCode() != HttpStatus.ACCEPTED)
            {
                return responseEntity;
            }

            Map<String, Object> result = new HashMap<>();

            String old_password = body.get("old_password");
            String new_password = body.get("new_password");

            User user = userService.findByEmail(principal.getName());

            if(user == null)
            {
                user = userService.findByMobileNo(principal.getName());
            }

            if(user == null)
            {
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }

            if(bCryptPasswordEncoder.matches(old_password, user.getPassword()))
            {
                user.setPassword(bCryptPasswordEncoder.encode(new_password));
                userService.save(user);

                result.put("password_changed", "Password Changed");
                return ResponseEntity.ok().body(result);
            }

            else
            {
                result.put("password_invalid", "Invalid Password");
                return ResponseEntity.badRequest().body(result);
            }
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }
}