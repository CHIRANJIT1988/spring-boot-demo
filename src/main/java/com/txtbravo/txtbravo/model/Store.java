package com.txtbravo.txtbravo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.txtbravo.txtbravo.entity.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "store")
@Where(clause="is_active=1")
@SQLDelete(sql = "UPDATE store" + " SET is_active = 0" + " WHERE store_id = ?")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private long id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name can't empty!")
    @Size(min = 3, max = 20, message = "First Name should be {min} and {max} characters.")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name can't empty!")
    @Size(min = 3, max = 20, message = "Last Name should be {min} and {max} characters.")
    private String lastName;

    @Column(name = "mobile_no")
    @NotEmpty(message = "Mobile number required!")
    @Size(min = 3, max = 20, message = "Invalid mobile number.")
    private String mobileNo;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;



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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}