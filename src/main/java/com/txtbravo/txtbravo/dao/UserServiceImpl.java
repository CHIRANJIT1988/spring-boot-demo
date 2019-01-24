package com.txtbravo.txtbravo.dao;

import com.txtbravo.txtbravo.model.Role;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.repository.UserRepository;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

//@Repository("mysql")
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{
    @Autowired
    UserRepository userJpaRepository;

    public User save(User user)
    {
        return userJpaRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable)
    {
        return userJpaRepository.findAll(pageable);
    }

    public User findById(Long id)
    {
        return userJpaRepository.findById(id);
    }

    @Override
    public User findByEmail(String email)
    {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User findByMobileNo(String mobileNo)
    {
        return userJpaRepository.findByMobileNo(mobileNo);
    }

    public void delete(User user)
    {
        userJpaRepository.delete(user);
    }

    /*private List<SimpleGrantedAuthority> getAuthority()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }*/

    private List<SimpleGrantedAuthority> getAuthority(User user)
    {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role : user.getRoles())
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return grantedAuthorities;
    }


    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException
    {
        User user;

        if(userId.contains("@"))
        {
            /**
             * Find user by email first
             */
            user = userJpaRepository.findByEmail(userId);
        }

        else
        {
            /**
             * If user not found by email find with mobile no
             */
            user = userJpaRepository.findByMobileNo(userId);
        }

        if(user != null)
        {
            /**
             * If user found by mobile no. return user
             */
            return new org.springframework.security.core.userdetails.User(userId.contains("@") ? user.getEmail() : user.getMobileNo(), user.getPassword(), getAuthority(user));
        }

        /**
         * If user not found by email and mobile no. throw exception
         */
        throw new UsernameNotFoundException("Invalid Email/MobileNo. or Password.");
    }
}