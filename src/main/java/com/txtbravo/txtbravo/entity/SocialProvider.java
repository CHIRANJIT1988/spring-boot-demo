package com.txtbravo.txtbravo.entity;

import com.txtbravo.txtbravo.model.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "social_provider")
@Where(clause="is_active=1")
@SQLDelete(sql = "UPDATE social_provider" + " SET is_active = 0" + " WHERE id = ?")
public class SocialProvider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "provider_id")
    @NotEmpty(message = "Provider id can not be empty")
    private String providerId;

    @Column(name = "provider")
    private String provider;

    @Column(name = "access_token")
    @NotEmpty(message = "Access token can not be empty")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public SocialProvider(String providerId, String provider, String accessToken, User user)
    {
        this.providerId = providerId;
        this.provider = provider;
        this.accessToken = accessToken;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}