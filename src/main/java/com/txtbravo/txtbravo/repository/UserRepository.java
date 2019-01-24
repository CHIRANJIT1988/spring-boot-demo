package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findById(Long id);
    User findByEmail(String email);
    User findByMobileNo(String mobileNo);
}