package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService
{
    User save(User user);

    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    User findByEmail(String email);

    User findByMobileNo(String mobileNo);

    void delete(User user);
}