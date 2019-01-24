package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.entity.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SocialProviderRepository extends JpaRepository<SocialProvider, Integer> {

    SocialProvider findByProviderId(String providerId);
}