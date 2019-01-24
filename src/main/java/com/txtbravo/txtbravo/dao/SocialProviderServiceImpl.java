package com.txtbravo.txtbravo.dao;

import com.txtbravo.txtbravo.entity.SocialProvider;
import com.txtbravo.txtbravo.model.Role;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.repository.SocialProviderRepository;
import com.txtbravo.txtbravo.repository.UserRepository;
import com.txtbravo.txtbravo.service.SocialProviderService;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Repository("mysql")
@Service(value = "providerService")
public class SocialProviderServiceImpl implements SocialProviderService
{
    @Autowired
    SocialProviderRepository socialProviderRepository;

    public SocialProvider save(SocialProvider socialProvider)
    {
        return socialProviderRepository.save(socialProvider);
    }

    public SocialProvider findById(String providerId)
    {
        return socialProviderRepository.findByProviderId(providerId);
    }

    public void delete(SocialProvider socialProvider)
    {
        socialProviderRepository.delete(socialProvider);
    }
}