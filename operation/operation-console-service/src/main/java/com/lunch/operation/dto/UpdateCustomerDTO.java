package com.lunch.operation.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;

/**
 * UpdateCustomerDTO
 *
 * @author torrisli
 * @date 2023/2/21
 * @Description: UpdateCustomerDTO
 */
public class UpdateCustomerDTO {

    @NotBlank(message = "客户标识")
    private String customerId;

    private String customerName;

    private String customerType;

    private String remark;

    private List<CustomerContactInfoDTO> customerContactInfos;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CustomerContactInfoDTO> getCustomerContactInfos() {
        return customerContactInfos;
    }

    public void setCustomerContactInfos(List<CustomerContactInfoDTO> customerContactInfos) {
        this.customerContactInfos = customerContactInfos;
    }
}
