package com.txtbravo.txtbravo.entity;

import com.txtbravo.txtbravo.model.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Where(clause="is_active=1")
@SQLDelete(sql = "UPDATE address" + " SET is_active = 0" + " WHERE address_id = ?")
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private long id;

    @Column(name = "full_name")
    @NotEmpty(message = "First name can't empty!")
    @Size(min = 3, max = 30, message = "First Name should be {min} and {max} characters.")
    private String fullName;

    @Column(name = "mobile_no", length = 10)
    @NotEmpty(message = "Mobile no. required!")
    @Size(message = "Invalid Mobile No.")
    private String mobileNo;

    @Column(name = "landmark")
    @NotEmpty(message = "Landmark can't empty!!")
    @Size(min = 10, max = 100, message = "Landmark should be {min} and {max} characters.")
    private String landmark;

    @Column(name = "locality")
    @NotEmpty(message = "Locality can't empty!!")
    @Size(min = 3, max = 50, message = "Locality should be {min} and {max} characters.")
    private String locality;

    @Column(name = "house_no")
    @NotEmpty(message = "House No. / Flat / Floor / Building can't empty!!")
    @Size(min = 3, max = 50, message = "Should be {min} and {max} characters.")
    private String houseNo;

    @Column(name = "state")
    @NotEmpty(message = "State can't empty!!")
    @Size(min = 3, max = 50, message = "State should be {min} and {max} characters.")
    private String state;

    @Column(name = "country")
    @NotEmpty(message = "Country can't empty!!")
    @Size(min = 3, max = 50, message = "Country should be {min} and {max} characters.")
    private String country;

    @Column(name = "pincode", length = 6)
    @NotEmpty(message = "Pincode required!")
    @Size(message = "Invalid Pincode.")
    private String pincode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
