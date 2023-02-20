package com.lunch.operation.model;

import com.lunch.common.enums.Gender;

/**
 * CustomerContactInfo
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: CustomerContactInfo
 */
public class CustomerContactInfo {

    private String name;

    private Gender gender;

    private String phone;

    private String email;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
