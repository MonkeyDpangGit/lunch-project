package com.lunch.operation.dto;

import com.lunch.operation.enums.CustomerType;
import java.util.List;
import javax.validation.constraints.NotBlank;

/**
 * CreateCustomerDTO
 *
 * @author torrisli
 * @date 2023/2/20
 * @Description: CreateCustomerDTO
 */
public class CreateCustomerDTO {

    @NotBlank(message = "客户名称")
    private String customerName;

    @NotBlank(message = "客户类型")
    private String customerType;

    private String remark;

    private List<CustomerContactInfoDTO> customerContactInfos;

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
