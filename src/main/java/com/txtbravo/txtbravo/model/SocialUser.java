package com.txtbravo.txtbravo.model;

public class SocialUser
{
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String name;
    private String birthday;
    private String location;
    private String hometown;
    private String gender;


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return new StringBuilder().append("id: ").append(id).append("\nname: ").append(name).
                append("\nfirst name: ").append(first_name).append("\nlast name: ").append(last_name).append("\nemail: ").
                append(email).append("\nhometown: ").append(hometown).append("\nlocation: ").append(location).append("\ngender: ").
                append(gender).append("\nbirthday: ").append(birthday).toString();
    }
}
