package com.lunch.operation.model;

import com.lunch.common.model.BaseModel;
import java.util.List;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Customer
 *
 * @author torrisli
 * @date 2023/2/17
 * @Description: Customer
 */
public class Customer extends BaseModel {

    @Indexed
    private String customerId;

    @Indexed
    private String customerName;

    private String type;

    private String remark;

    private List<CustomerContactInfo> customerContactInfos;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CustomerContactInfo> getCustomerContactInfos() {
        return customerContactInfos;
    }

    public void setCustomerContactInfos(List<CustomerContactInfo> customerContactInfos) {
        this.customerContactInfos = customerContactInfos;
    }
}
