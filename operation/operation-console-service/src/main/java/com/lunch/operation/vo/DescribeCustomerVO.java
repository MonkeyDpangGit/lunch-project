package com.lunch.operation.vo;

import com.lunch.operation.model.CustomerContactInfo;
import java.util.Date;
import java.util.List;

/**
 * DescribeCustomerVO
 *
 * @author torrisli
 * @date 2023/2/21
 * @Description: DescribeCustomerVO
 */
public class DescribeCustomerVO {

    private String customerId;

    private String customerName;

    private String customerType;

    private String remark;

    private List<CustomerContactInfoVO> customerContactInfos;

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

    public List<CustomerContactInfoVO> getCustomerContactInfos() {
        return customerContactInfos;
    }

    public void setCustomerContactInfos(List<CustomerContactInfoVO> customerContactInfos) {
        this.customerContactInfos = customerContactInfos;
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
