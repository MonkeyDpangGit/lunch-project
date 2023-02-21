package com.lunch.operation.dto;

import com.lunch.common.enums.Gender;
import javax.validation.constraints.NotBlank;

/**
 * CustomerContactInfoDTO
 *
 * @author torrisli
 * @date 2023/2/20
 * @Description: CustomerContactInfoDTO
 */
public class CustomerContactInfoDTO {

    @NotBlank(message = "姓名")
    private String name;

    @NotBlank(message = "性别")
    private String gender;

    @NotBlank(message = "手机号码")
    private String phone;

    @NotBlank(message = "电话地区号")
    private String phoneAreaCode;

    private String email;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
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
