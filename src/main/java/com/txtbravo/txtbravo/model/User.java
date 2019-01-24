package com.txtbravo.txtbravo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.txtbravo.txtbravo.entity.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user")
@Where(clause="is_active=1")
@SQLDelete(sql = "UPDATE user" + " SET is_active = 0" + " WHERE user_id = ?")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name can't empty!")
    @Size(min = 3, max = 20, message = "First Name should be {min} and {max} characters.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name can't empty!")
    @Size(min = 3, max = 20, message = "Last Name should be {min} and {max} characters.")
    private String lastName;

    @Column(name = "mobile_no", length = 10)
    @Size(message = "Invalid mobile number.")
    private String mobileNo;

    @Email(message = "Please enter valid email")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles;


    public User()
    {

    }

    public User(String email, String firstName, String lastName, Set<Role> roles)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}