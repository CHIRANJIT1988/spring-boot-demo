package com.txtbravo.txtbravo.dao;

import com.txtbravo.txtbravo.model.Store;
import com.txtbravo.txtbravo.repository.StoreRepository;
import com.txtbravo.txtbravo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

//@Repository("mysql")
@Service(value = "storeService")
public class StoreServiceImpl implements UserDetailsService, StoreService
{
    @Autowired
    StoreRepository storeRepository;

    public Store save(Store store)
    {
        return storeRepository.save(store);
    }

    public Page<Store> findAll(Pageable pageable)
    {
        return storeRepository.findAll(pageable);
    }

    public Store findById(Long id)
    {
        return storeRepository.findById(id);
    }

    public void delete(Store store)
    {
        storeRepository.delete(store);
    }

    private List<SimpleGrantedAuthority> getAuthority()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /*private List<SimpleGrantedAuthority> getAuthority(User user)
    {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role : user.getRoles())
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return grantedAuthorities;
    }*/


    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException
    {
        Store store = storeRepository.findByUsername(userId);

        if(store == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(store.getUsername(), store.getPassword(), getAuthority());
    }
}