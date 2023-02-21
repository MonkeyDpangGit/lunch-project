package com.lunch.operation.dto;

import javax.validation.constraints.NotBlank;

/**
 * DescribeCustomerDTO
 *
 * @author torrisli
 * @date 2023/2/21
 * @Description: DescribeCustomerDTO
 */
public class DescribeCustomerDTO {

    @NotBlank(message = "客户标识")
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
