package com.lunch.operation.vo;

import com.lunch.operation.enums.CustomerType;
import java.util.Date;

/**
 * ListCustomersVO
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: ListCustomersVO
 */
public class ListCustomersVO {

    private String customerId;

    private String customerName;

    private CustomerType customerType;

    private String customerTypeDisplayName;

    private Date createdDate;

    private Date lastModifiedDate;

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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeDisplayName() {
        return customerTypeDisplayName;
    }

    public void setCustomerTypeDisplayName(String customerTypeDisplayName) {
        this.customerTypeDisplayName = customerTypeDisplayName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
