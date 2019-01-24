package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.entity.SocialProvider;

public interface SocialProviderService
{
    SocialProvider save(SocialProvider socialProvider);

    SocialProvider findById(String providerId);

    void delete(SocialProvider socialProvider);
}