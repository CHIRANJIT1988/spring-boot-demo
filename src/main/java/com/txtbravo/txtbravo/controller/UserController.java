package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.model.Role;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.service.RoleService;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/rest/user")
public class UserController
{
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<User> findAll(@PageableDefault(size = 20) Pageable pageable)
    {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id)
    {
        User user = userService.findById(id);

        if(user == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@Valid @RequestBody User user, BindingResult bindingResults)
    {
        if(!bindingResults.hasErrors())
        {
            Map<String, Object> result = new HashMap<>();

            boolean isEmailExist = userService.findByEmail(user.getEmail()) != null;
            boolean isMobileNoExist = userService.findByMobileNo(user.getMobileNo()) != null;

            if(isEmailExist)
            {
                result.put("email", "Email already registered");
            }

            if(isMobileNoExist)
            {
                result.put("mobile_no", "Mobile No. already registered");
            }

            if(result.size() != 0)
            {
                return ResponseEntity.badRequest().body(result);
            }

            /**
             * BCrypt Password and store
             */
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            //Role userRole = roleService.findByRole("ROLE_ADMIN");
            //user.setRoles(new HashSet<>(Arrays.asList(userRole)));

            Set<Role> grantedAuthorities = new HashSet<>();

            for(Role role : user.getRoles())
            {
                Role userRole = roleService.findByRole(role.getRole());
                grantedAuthorities.add(userRole);
            }

            user.setRoles(new HashSet<>(grantedAuthorities));

            return userService.save(user);
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody User _user, BindingResult bindingResults)
    {

        if(!bindingResults.hasErrors())
        {
            User user = userService.findById(id);

            if(user == null)
            {
                return ResponseEntity.notFound().build();
            }

            user.setFirstName(_user.getFirstName());
            user.setLastName(_user.getLastName());
            user.setMobileNo(_user.getMobileNo());
            user.setEmail(_user.getEmail());

            Set<Role> grantedAuthorities = new HashSet<>();

            for(Role role : _user.getRoles())
            {
                Role userRole = roleService.findByRole(role.getRole());
                grantedAuthorities.add(userRole);
            }

            user.setRoles(new HashSet<>(grantedAuthorities));

            return ResponseEntity.ok().body(userService.save(user));
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> delete(@PathVariable(value = "id") Long id)
    {
        User user = userService.findById(id);

        if(user == null)
        {
            return ResponseEntity.notFound().build();
        }

        userService.delete(user);

        return ResponseEntity.ok().body(user);
    }
}